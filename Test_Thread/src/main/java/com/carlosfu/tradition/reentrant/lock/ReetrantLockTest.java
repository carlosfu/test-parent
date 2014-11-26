package com.carlosfu.tradition.reentrant.lock;


/**
 * 重入锁演示
 * @author leifu
 * @Time 2014-9-28
 */
public class ReetrantLockTest {

	public static void main(String[] args) {
		final LockMethodClass methodClass = new LockMethodClass();
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//1. 普通锁 是满足不了重入性的
//						methodClass.methodLockOutter();
						//2. 直接用synchronized可以满足重入性
//						methodClass.methodSynOutter();
						//3. 自定义重入锁可以满足重入性
//						methodClass.methodReentrantOutter();
						//4. 直接用jdk中的重入锁
						methodClass.methodJDKReentrantOutter();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			thread.start();
		}
	}
}
