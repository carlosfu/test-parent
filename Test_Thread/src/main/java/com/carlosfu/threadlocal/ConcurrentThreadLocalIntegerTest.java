package com.carlosfu.threadlocal;

import java.util.Random;

/**
 * ThreadLocal Integer演示
 * 
 * @author leifu
 * @Date 2015年1月13日
 * @Time 上午10:48:53
 */
public class ConcurrentThreadLocalIntegerTest {

    private static ThreadLocal<Integer> threadData = new ThreadLocal<Integer>();

    private static int data;

    public static void main(String[] args) {

        // 3个线程,每个线程都随机往ThreadLocal set一个值.
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data = new Random().nextInt(100);
                    threadData.set(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            int data = threadData.get();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }

    static class B {
        public void get() {
            int data = threadData.get();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }

}
