package com.carlosfu.hystrix.helloworld;

import static org.junit.Assert.*;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import com.carlosfu.util.HttpRequestUtil;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
    	// 使用线程隔离时，调用超时时间，默认:1秒  
    	// HystrixProperty<Integer> executionIsolationThreadTimeoutInMilliseconds;
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return "Hello " + name + "!";
    }

    public static class UnitTest {

        @Test
        public void testSynchronous() {
            assertEquals("Hello World!", new CommandHelloWorld("World").execute());
            assertEquals("Hello Bob!", new CommandHelloWorld("Bob").execute());
        }

        @Test
        public void testAsynchronous1() throws Exception {
            assertEquals("Hello World!", new CommandHelloWorld("World").queue().get());
            assertEquals("Hello Bob!", new CommandHelloWorld("Bob").queue().get());
        }

        @Test
        public void testAsynchronous2() throws Exception {

            Future<String> fWorld = new CommandHelloWorld("World").queue();
            Future<String> fBob = new CommandHelloWorld("Bob").queue();

            assertEquals("Hello World!", fWorld.get());
            assertEquals("Hello Bob!", fBob.get());
        }
        

        @Test
        public void testObservable() throws Exception {

            Observable<String> fWorld = new CommandHelloWorld("World").observe();
            Observable<String> fBob = new CommandHelloWorld("Bob").observe();

            // blocking
            assertEquals("Hello World!", fWorld.toBlockingObservable().single());
            assertEquals("Hello Bob!", fBob.toBlockingObservable().single());

            // non-blocking 
            // - this is a verbose anonymous inner-class approach and doesn't do assertions
            fWorld.subscribe(new Observer<String>() {

                @Override
                public void onCompleted() {
                    // nothing needed here
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(String v) {
                    System.out.println("onNext: " + v);
                }

            });

            // non-blocking
            // - also verbose anonymous inner-class
            // - ignore errors and onCompleted signal
            fBob.subscribe(new Action1<String>() {

                @Override
                public void call(String v) {
                    System.out.println("onNext: " + v);
                }

            });
        }
    }

}
