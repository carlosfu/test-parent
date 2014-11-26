package com.carlosfu.visualvm.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * oom简单演示, 查看笔记visualvm测试
 * @author leifu
 * @Time 2014年10月5日
 */
public class OomTest {
	public static void main(String[] args) throws InterruptedException {
		// 给visualvm留点时间
		int seconds = 20;
		while (seconds > 0) {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("还剩下: " + seconds + "秒");
			seconds--;
		}
		System.out.println("========开始测试=========");

		Long i = 0L;
		List<Long> list = new ArrayList<Long>();
		while (true) {
			list.add(new Long(i++));
		}
	}
}
