package com.carlosfu.visualvm.method.speed;

import java.util.concurrent.TimeUnit;

/**
 * 方法速度测试，使用profiler演示，查看笔记-visualvm测试
 * 
 * @author leifu
 * @Time 2014年10月5日
 */
public class SlowSpeedTest {
	public static void method1(int seconds) throws InterruptedException {
		System.out.println("=======method1 excute need: " + seconds
				+ " seconds=========");
		TimeUnit.SECONDS.sleep(seconds);
	}

	public static void method2(int seconds) throws InterruptedException {
		System.out.println("=======method2 excute need: " + seconds
				+ " seconds=========");
		TimeUnit.SECONDS.sleep(seconds);
	}

	public static void method3(int seconds) throws InterruptedException {
		System.out.println("=======method3 excute need: " + seconds
				+ " seconds=========");
		TimeUnit.SECONDS.sleep(seconds);
	}

	public static void main(String[] args) throws InterruptedException {
		// 为了准备visualvm
		TimeUnit.SECONDS.sleep(20);
		System.out.println("==========main start===========");
		method1(2);
		method2(3);
		System.out.println("==========main sleep 1 seconds==============");
		TimeUnit.SECONDS.sleep(1);
		method3(5);
		System.out.println("==========main end===========");
	}
}
