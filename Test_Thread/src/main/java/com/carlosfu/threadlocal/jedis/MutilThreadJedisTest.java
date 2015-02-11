package com.carlosfu.threadlocal.jedis;

import java.util.Random;

/**
 * 多线程模拟 jedis统计耗时
 * @author leifu
 * @Date 2015年2月11日
 * @Time 下午5:35:49
 */
public class MutilThreadJedisTest {
    public static void main(String[] args) {
        final Jedis jedis = new Jedis();
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int sleepSeconds = new Random().nextInt(5);
                    System.out.println("sleepSeconds: " + sleepSeconds);
                    jedis.set("hello", "world" + sleepSeconds, sleepSeconds);
                }
            });
            thread.setName("jedis-thread" + i);
            thread.start();
        }
    }
}
