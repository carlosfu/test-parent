package com.sohu.tv.redis;

import com.sohu.tv.builder.ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPool;

/**
 * jedisPool获取工具
 * 
 * @author leifu
 * @Date 2015年8月4日
 * @Time 上午9:01:45
 */
public class RedisStandAloneUtil {

    private final static Logger logger = LoggerFactory.getLogger(RedisStandAloneUtil.class);

    /**
     * jedis连接池
     */
    private static JedisPool jedisPool;
    
    /**
     * cachecloud应用id
     */
    private final static int appId = 10228;

    static {
        try {
            jedisPool = ClientBuilder.redisStandalone(appId).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }
    
    public static void main(String[] args) {
        System.out.println(RedisStandAloneUtil.getJedisPool().getResource().info());
    }

}
