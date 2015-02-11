package com.carlosfu.threadlocal.jedis;

/**
 * 模拟jedis的结构
 * @author leifu
 * @Date 2015年2月11日
 * @Time 下午5:17:13
 */
public class Jedis {
    
    private Client client = new Client();

    /**
     * jedis中命令都是sendCommnad和process两个步骤，所以可以利用threadLocal来统计时间
     * @param key
     * @param value
     * @param seconds
     */
    public void set(String key, String value, int seconds) {
        client.sendCommand("set key " + key + ", value " + value, seconds);
        client.process(seconds);
    }
}
