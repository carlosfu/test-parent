package com.carlosfu.tradition.match.wait_notify;

public class Starter {
	public synchronized void shoutAndStart() throws InterruptedException{
		System.out.println("==============starter: 发令员5秒后开枪比赛==============");
		Thread.sleep(5000);
		System.out.println("==============starter: 打枪比赛开始================");
		this.notify();
	}
}
