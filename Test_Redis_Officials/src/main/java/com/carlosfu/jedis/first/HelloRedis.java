package com.carlosfu.jedis.first;

import redis.clients.jedis.Jedis;

/**
 * 第一个简单的jedis测试
 * @author leifu
 * @Date 2014-10-30
 * @Time 上午8:46:26
 */
public class HelloRedis {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("10.16.15.86");
		String key = "hello";
		jedis.set(key, "redisWorld1");
		String value = jedis.get(key);
		System.out.println("value is: " + value);
		jedis.close();
	}
}
