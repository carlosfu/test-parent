package com.carlosfu.tradition.dead.lock;

/**
 * 死锁测试
 * @author leifu
 * @Time 2014年9月24日
 */
public class DeadLockTest {
    public static void main(String[] args) {
        
        LockA lockA = new LockA();
        LockB lockB = new LockB();
        final DeadLockClass deadLockTest = new DeadLockClass(lockA, lockB);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLockTest.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    deadLockTest.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
