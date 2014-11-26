package com.carlosfu.tradition.synchronzied.compare;

/**
 * 1个对象，两个线程，分别调用普通方法锁，会锁住线程
 * 
 * @author leifu
 * @Time 2014年9月18日
 */
public class Test2 {
    private static MyUtil m1 = new MyUtil();

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				m1.workRegular(10);
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				m1.workRegular(5);
			}
		}).start();
	}
}
