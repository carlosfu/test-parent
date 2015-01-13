package com.carlosfu.threadlocal;

import java.util.Random;

/**
 * 对象类型的ThreadLocal
 * 
 * @author leifu
 * @Date 2015年1月13日
 * @Time 上午10:13:23
 */
public class ConcurrentThreadLocalComplexObjectTestBest {

    private static ThreadLocal<SingleInstanceModel> threadData = new ThreadLocal<SingleInstanceModel>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt(100);
                    SingleInstanceModel model = SingleInstanceModel.getInstanceModel(threadData);
                    model.setInfo("hehe:" + data);
                    model.setName("name:" + data);
                    threadData.set(model);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            SingleInstanceModel model = threadData.get();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get singleModel data: " + model);
        }
    }

    static class B {
        public void get() {
            SingleInstanceModel model = threadData.get();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get singleModel data: " + model);
        }
    }

}
