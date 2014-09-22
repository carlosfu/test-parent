package com.carlosfu.syn.statics;

/**
 * 1个对象，1个静态函数调用，两个线程，分别调用普通方法锁，静态函数(有synchronized)，不会锁住线程
 * 
 * @author leifu
 * @Time 2014年9月18日
 */
public class Test3 {
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
				MyUtil.workStatic(5);
			}
		}).start();
	}
}
