
package com.carlosfu.hystrix.fallback;

import static org.junit.Assert.*;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.HystrixRequestLog;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;


public class CommandWithFallbackViaNetwork extends HystrixCommand<String> {
    private final int id;

    protected CommandWithFallbackViaNetwork(int id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueCommand")));
        this.id = id;
    }

    @Override
    protected String run() {
        throw new RuntimeException("force failure for example");
    }

    @Override
    protected String getFallback() {
        return new FallbackViaNetwork(id).execute();
    }

    private static class FallbackViaNetwork extends HystrixCommand<String> {
        private final int id;

        public FallbackViaNetwork(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueFallbackCommand"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("RemoteServiceXFallback")));
            this.id = id;
        }

        @Override
        protected String run() {
            throw new RuntimeException("the fallback also failed");
        }

        @Override
        protected String getFallback() {
            return null;
        }
    }

    public static class UnitTest {

        @Test
        public void test() {
        	/**
        	 * 通过这个context可以查看执行过程中的相关参数
        	 */
            HystrixRequestContext context = HystrixRequestContext.initializeContext();
            try {
                assertEquals(null, new CommandWithFallbackViaNetwork(1).execute());

                HystrixCommand<?> command1 = HystrixRequestLog.getCurrentRequest().getExecutedCommands().toArray(new HystrixCommand<?>[2])[0];
                assertEquals("GetValueCommand", command1.getCommandKey().name());
                assertTrue(command1.getExecutionEvents().contains(HystrixEventType.FAILURE));

                HystrixCommand<?> command2 = HystrixRequestLog.getCurrentRequest().getExecutedCommands().toArray(new HystrixCommand<?>[2])[1];
                assertEquals("GetValueFallbackCommand", command2.getCommandKey().name());
                assertTrue(command2.getExecutionEvents().contains(HystrixEventType.FAILURE));
            } finally {
                context.shutdown();
            }
        }
    }
}
