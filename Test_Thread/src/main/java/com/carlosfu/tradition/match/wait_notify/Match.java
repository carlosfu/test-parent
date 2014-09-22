package com.carlosfu.tradition.match.wait_notify;

/**
 * 比赛
 * @author leifu
 * @Time 2014年9月18日
 */
public class Match {
	public static void main(String[] args) throws InterruptedException {
		final Starter starter = new Starter();
		final Runner runner = new Runner(starter);
		System.out.println("=============match:运动员一直准备=============");
		
		//运动员线程(运动员对象持有发令员对象，这样就可以wait和notify了)
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					runner.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		//发令员线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					starter.shoutAndStart();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		
	}
}
