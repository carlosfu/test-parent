package com.carlosfu.threadlocal.jedis;

import java.util.concurrent.TimeUnit;

/**
 * 模拟jedis中Protocol
 * @author leifu
 * @Date 2015年2月11日
 * @Time 下午5:34:50
 */
public class Protocol {
    
    public static void sendCommand(String command, int seconds) {
        System.out.println("command: " + command + " start execute!");
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("command: " + command + " finish execute, cost " + seconds + " s!");
    }

    public static void process(int seconds) {
        System.out.println("process start execute!");
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("process finish execute, cost " + seconds + " s!");
    }
}
