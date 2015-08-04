package com.carlosfu.tradition.fair.lock;

/**
 * 普通同步锁
 * 
 * @author leifu
 * @Time 2014年9月26日
 */
public class SynLockTest {
    private static LockClass lockClass = new LockClass();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 使用synchronized实现的
                        lockClass.methodSyn();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
