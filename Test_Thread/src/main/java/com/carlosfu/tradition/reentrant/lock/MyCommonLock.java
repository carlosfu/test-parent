package com.carlosfu.tradition.reentrant.lock;

/**
 * 简单锁，满足不了重入性(两次重入会卡死在while(isLocked))
 * @author leifu
 * @Time 2014-9-28
 */
public class MyCommonLock {
	private volatile boolean isLocked = false;

	public synchronized void lock() throws InterruptedException {
		while (isLocked) {
			this.wait();
		}
		isLocked = true;
	}

	public synchronized void unlock() {
		isLocked = false;
		this.notify();
	}
}
