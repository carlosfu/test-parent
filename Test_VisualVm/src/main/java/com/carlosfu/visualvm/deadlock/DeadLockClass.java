package com.carlosfu.visualvm.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁测试类，查看笔记visualvm测试
 * @author leifu
 * @Time 2014年10月5日
 */
public class DeadLockClass {
	private LockA lockA = new LockA();
	private LockB lockB = new LockB();
	public static void main(String[] args) {
		final DeadLockClass deadLockTest = new DeadLockClass();
		new Thread(new Runnable() {//线程1
			@Override
			public void run() {
				try {
					deadLockTest.method1();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {//线程2
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

	// 先用A锁，再用B锁
	public void method1() throws InterruptedException {
		synchronized (lockA) {
			System.out.println("Method1 sleep 2 seconds");
			TimeUnit.SECONDS.sleep(2);
			synchronized (lockB) {
				System.out.println("======enter method1======");
			}
		}
	}
	// 先用B锁，再用A锁
	public void method2() throws InterruptedException {
		synchronized (lockB) {
			System.out.println("Method2 sleep 3 seconds");
			TimeUnit.SECONDS.sleep(3);
			synchronized (lockA) {
				System.out.println("======enter method2======");
			}
		}
	}

}
class LockA {}
class LockB {}