package com.carlosfu.tradition.match.wait_notify.v2;

/**
 * 比赛
 * 
 * @author leifu
 * @Time 2014年9月18日
 */
public class MatchV2 {
	/**
	 * 自定义的wait-notify工具
	 */
	private static MyWaitNotify myWaitNotify = new MyWaitNotify();

	/**
	 * 发令员
	 */
	private static StarterV2 starter = new StarterV2(myWaitNotify);
	
	/**
	 * 运动员
	 */
	private static RunnerV2 runner = new RunnerV2(myWaitNotify);

	public static void main(String[] args) throws InterruptedException {

		System.out.println("=============match:运动员一直准备=============");

		// 运动员线程(运动员对象持有发令员对象，这样就可以wait和notify了)
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
		// 发令员线程
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
