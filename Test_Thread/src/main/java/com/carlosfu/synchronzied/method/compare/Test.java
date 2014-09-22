package com.carlosfu.synchronzied.method.compare;

/**
 * 两个对象，两个线程，分别调用普通方法锁，不会锁住线程
 * 
 * @author leifu
 * @Time 2014年9月18日
 */
public class Test {
    private static MyUtil m1 = new MyUtil();
    private static MyUtil m2 = new MyUtil();

    public static void main(String[] args) {

        // 线程1
        new Thread(new Runnable() {

            @Override
            public void run() {
                m1.workRegular(10);
            }
        }).start();

        // 线程2
        new Thread(new Runnable() {

            @Override
            public void run() {
                m2.workRegular(5);
            }
        }).start();
    }
}
