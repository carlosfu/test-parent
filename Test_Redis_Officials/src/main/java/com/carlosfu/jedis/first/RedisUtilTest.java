package com.carlosfu.jedis.first;

import java.util.concurrent.CountDownLatch;

import com.carlosfu.jedis.jedis.RedisUtil;


/**
 * 第一个简单的jedis测试
 * @author leifu
 * @Date 2014-10-30
 * @Time 上午8:46:26
 */
public class RedisUtilTest {
	public static void main(String[] args) {
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		String key = "hello";
		String value = RedisUtil.getClient().get(key);
		System.out.println("value: " + value);
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
