package com.carlosfu.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 简单自定义ThreadLocal功能
 * @author leifu
 * @Date 2015年1月13日
 * @Time 上午10:49:40
 */
public class CustomThreadLocalTest {

    private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();

    private static int data;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data = new Random().nextInt(100);
                    threadData.put(Thread.currentThread(), data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
        //没用join
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(threadData);
    }

    static class A {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }

    static class B {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }

}
