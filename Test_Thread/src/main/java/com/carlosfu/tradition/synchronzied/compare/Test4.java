package com.carlosfu.tradition.synchronzied.compare;

/**
 * 两个线程同时调用不同的静态函数(都有synchronized)，会锁住线程
 * 
 * @author leifu
 * @Time 2014年9月18日
 */
public class Test4 {
    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                MyUtil.workStatic(10);
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                MyUtil.workStatic2(5);
            }
        }).start();
    }
}
