package com.carlosfu.tradition.match.wait_notify.v2;

import java.util.concurrent.TimeUnit;

/**
 * 运动员
 * 
 * @author leifu
 * @Time 2014年9月18日
 */
public class RunnerV2 {

	private MyWaitNotify myWaitNotify;

	public RunnerV2(MyWaitNotify myWaitNotify) {
		this.myWaitNotify = myWaitNotify;
	}

	public void run() throws InterruptedException {
		System.out.println("============runner:等待枪声================");

		// 自定义的waitnotify
		myWaitNotify.doWait();

		System.out.println("=============runner: 运动员听到枪声，开始跑了===========");
		System.out.println("=============runner: 运动员预计跑3秒==========");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("=============runner: 运动员跑完结束===============");
	}
}
