package com.carlosfu.test.hystrix.first;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.carlosfu.hystrix.first.HelloWorldCommand;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

public class HelloWorldCommandTest2 {

    @Test
    public void testOne() throws InterruptedException {
        // 注册观察者事件拦截
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("World", "observerGroup");
        Observable<String> fs = helloWorldCommand.observe();
        // 注册结果回调事件
        fs.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                // 执行结果处理,result 为HelloWorldCommand返回的结果
                // 用户对结果做二次处理.
                System.out.println("result: " + result);
            }
        });
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void testTwo() throws InterruptedException {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("World", "observerGroup");
        // 注册观察者事件拦截
        Observable<String> fs = helloWorldCommand.observe();
        // 注册完整执行生命周期事件
        fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                // onNext/onError完成之后最后回调
                System.out.println("execute onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                // 当产生异常时回调
                System.out.println("onError " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                // 获取结果后回调
                System.out.println("onNext: " + v);
            }
        });

        TimeUnit.SECONDS.sleep(10);
    }

}
