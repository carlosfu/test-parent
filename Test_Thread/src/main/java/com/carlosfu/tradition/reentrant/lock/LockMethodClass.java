package com.carlosfu.tradition.reentrant.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁方法演示
 * 
 * @author leifu
 * @Time 2014-9-28
 */
public class LockMethodClass {
	/**
	 * 普通锁(用自旋锁实现的)
	 */
	private MyCommonLock myCommonLock = new MyCommonLock();

	/**
	 * 自定义重入锁
	 */
	private MyReentrantLock myReentrantLock = new MyReentrantLock();

	/**
	 * JDK中的重入锁
	 */
	private ReentrantLock reetrantLock = new ReentrantLock();

	/**
	 * 普通的锁 不能实现重入性
	 * 
	 * @throws InterruptedException
	 */
	public void methodLockOutter() throws InterruptedException {
		myCommonLock.lock();
		System.out.println("=======doSomething lock methodOutter start=======");
		methodLockInner();
		myCommonLock.unlock();
		System.out.println("=======doSomething lock methodOutter end=======");
	}

	private void methodLockInner() throws InterruptedException {
		myCommonLock.lock();
		System.out.println("=======doSomething lock methodInner=======");
		TimeUnit.SECONDS.sleep(2);
		myCommonLock.unlock();
	}

	/**
	 * 用synchronized演示重入锁
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void methodSynOutter() throws InterruptedException {
		System.out.println("=======doSomething syn methodOutter start=======");
		methodSynInner();
		System.out.println("=======doSomething syn methodOutter end=======");
	}

	private synchronized void methodSynInner() throws InterruptedException {
		System.out.println("=======doSomething syn methodInner=======");
		TimeUnit.SECONDS.sleep(2);
	}

	/**
	 * 自定义重入锁 实现重入性
	 * 
	 * @throws InterruptedException
	 */
	public void methodReentrantOutter() throws InterruptedException {
		myReentrantLock.lock();
		System.out
				.println("=======doSomething reentrant methodOutter start=======");
		methodReentrantInner();
		myReentrantLock.unlock();
		System.out
				.println("=======doSomething reentrant methodOutter end=======");
	}

	private void methodReentrantInner() throws InterruptedException {
		myReentrantLock.lock();
		System.out.println("=======doSomething reentrant methodInner=======");
		TimeUnit.SECONDS.sleep(2);
		myReentrantLock.unlock();
	}

	/**
	 * jdk自带的重入锁，实现重入性
	 * 
	 * @throws InterruptedException
	 */
	public void methodJDKReentrantOutter() throws InterruptedException {
		reetrantLock.lock();
		System.out.println("===doSomething JDK reentrant methodOutter start====");
		methodJDKReentrantInner();
		reetrantLock.unlock();
		System.out.println("===doSomething JDK reentrant methodOutter end===");
	}

	private void methodJDKReentrantInner() throws InterruptedException {
		reetrantLock.lock();
		System.out.println("=======doSomething JDK reentrant methodInner=======");
		TimeUnit.SECONDS.sleep(2);
		reetrantLock.unlock();
	}

}
