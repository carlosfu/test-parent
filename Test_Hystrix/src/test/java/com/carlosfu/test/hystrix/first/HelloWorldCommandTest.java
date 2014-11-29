package com.carlosfu.test.hystrix.first;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.carlosfu.hystrix.first.HelloWorldCommand;

/**
 * 第一个helloWorldCommand测试
 * 
 * @author leifu
 * @Date 2014年11月28日
 * @Time 下午3:31:31
 */
public class HelloWorldCommandTest {

    @Test
    public void testSynHystrix() {
        // 每个Command对象只能调用一次,不可以重复调用,
        // 重复调用对应异常信息:This instance can only be executed once. Please
        // instantiate a new instance.
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("World-hystrix-syn",
                "Synchronous-hystrix");
        helloWorldCommand.setCommandCostTime(0);
        // 使用execute()同步调用代码,效果等同于:helloWorldCommand.queue().get();
        String result = helloWorldCommand.execute();
        System.out.println("============Syn result=" + result + "============");
    }

    @Test
    public void testAsynHystrix() {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("World-hystrix-asyn",
                "Asynchronous-hystrix");
        helloWorldCommand.setCommandCostTime(1);
        // 异步调用,可自由控制获取结果时机,
        Future<String> future = helloWorldCommand.queue();
        // get操作不能超过command定义的超时时间,默认:1秒
        String result = null;
        try {
            result = future.get(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("============aSyn result=" + result + "============");
    }

}
