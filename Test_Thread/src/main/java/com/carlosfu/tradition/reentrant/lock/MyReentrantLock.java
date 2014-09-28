package com.carlosfu.tradition.reentrant.lock;

/**
 * 自定义简易的重入锁
 * 
 * @author leifu
 * @Time 2014-9-28
 */
public class MyReentrantLock {
	private volatile boolean isLocked = false;

	private Thread currentLockedThread = null;

	private int count = 0;

	public synchronized void lock() throws InterruptedException {
		Thread callThread = Thread.currentThread();

		// 如果没有锁住，或者锁住的是同一个线程(为了重入), 那么都会跳过while
		while (isLocked && callThread != currentLockedThread) {
			this.wait();
		}
		count++; // 累加,为了notify做计数
		isLocked = true;
		currentLockedThread = callThread;
	}

	public synchronized void unlock() {
		if (Thread.currentThread() == currentLockedThread) {
			count--;
			if (count == 0) {
				isLocked = false;
				this.notify();
			}
		}
	}

}
