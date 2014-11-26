package com.carlosfu.tradition.match.wait_notify.v2;

/**
 * 发令员
 * @author leifu
 * @Time 2014年9月22日
 */
public class StarterV2 {
	private MyWaitNotify myWaitNotify;

	public StarterV2(MyWaitNotify myWaitNotify) {
		this.myWaitNotify = myWaitNotify;
	}

	public synchronized void shoutAndStart() throws InterruptedException {
		System.out.println("==============starter: 发令员5秒后开枪比赛==============");
		Thread.sleep(5000);
		System.out.println("==============starter: 打枪比赛开始================");
		//自定义的wait notify
		myWaitNotify.doNotify();
	}
}
