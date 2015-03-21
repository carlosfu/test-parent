package com.carlosfu.tradition.thread.interrupte;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试线程中断
 * 
 * @author leifu
 * @Date 2015年2月13日
 * @Time 上午9:58:08
 */
public class TestThreadInterrupted {

    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Interrupted:" + Thread.interrupted());
        } else {
            System.out.println("Not Interrupted" + Thread.interrupted());
        }
        System.out.println("3");
    }

    public static void main23(String[] args) {
        Thread.currentThread().interrupt();
        // 下面的判断会打印出来这样的语句 Interrupt：false
        // 这样的结果是不是很让人费解呢？？？
        //
        // 分析其原因为：
        //
        // 因为在上面的一句代码可以中断线程，所以if判断线程是否中断就会得到的事true
        // 但是 Thread.interrupted()这个方法执行完后就会清除线程的中断状态，
        // 所以最后再次的打印Thread.interrupted()方法就会得到false结果。
        if (Thread.interrupted())
            System.out.println("Interrupted:" + Thread.interrupted());
        else
        {
            System.out.println("Not Interrupted" + Thread.interrupted());
        }

    }

    public static void main2(String[] args) {
        final AtomicInteger counter = new AtomicInteger();
        Thread thread = new Thread("interrupt test") {
            @Override
            public void run() {
                while (true) {
                    counter.getAndIncrement();
                    if (counter.get() == 10) {
                        Thread.currentThread().interrupt();
                    }
                    if (Thread.interrupted()) {
                        System.out.println("thread is interrupted");
                        break;
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}
