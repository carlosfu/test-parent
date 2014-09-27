package com.carlosfu.tradition.fair.lock;

/**
 * 自定义锁
 * @author leifu
 * @Time 2014年9月26日
 */
public class CustomLockTest {
    public static void main(String[] args) {
        final LockClass lockClass = new LockClass();
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //使用wait notify实现的Lock
                        lockClass.methodCustomLock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
