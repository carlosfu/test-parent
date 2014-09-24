package com.carlosfu.tradition.dead.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁测试类
 * @author leifu
 * @Time 2014年9月24日
 */
public class DeadLockClass {
    private LockA lockA;
    private LockB lockB;
    public DeadLockClass(LockA lockA, LockB lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    //先用A锁，再用B锁
    public void method1() throws InterruptedException {
        synchronized (lockA) {
            System.out.println("Method1 sleep 2 seconds");
            TimeUnit.SECONDS.sleep(2);
            synchronized (lockB) {
                System.out.println("===============enter method1=================");
            }
        }
    }
    //先用B锁，再用A锁
    public void method2() throws InterruptedException {
        synchronized (lockB) {
            System.out.println("Method2 sleep 3 seconds");
            TimeUnit.SECONDS.sleep(3);
            synchronized (lockA) {
                System.out.println("===============enter method2=================");
            }
        }
    }
}
