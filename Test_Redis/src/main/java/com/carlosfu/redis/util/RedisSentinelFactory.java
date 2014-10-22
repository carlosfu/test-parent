package com.carlosfu.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * redis哨兵工厂（来自投放项目）
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午5:28:55
 */
public class RedisSentinelFactory {
    private static final Logger logger = LoggerFactory.getLogger(RedisSentinelFactory.class);
    private static JedisSentinelPool jedisSentinelPool;

    static {
        Properties properties = RedisUtil.getRedisProperties();
        try {
            String masterName = properties.getProperty("redis.sentinel.masterName");
            String sentinels = properties.getProperty("redis.sentinel.list");
            Set<String> sentinelSet = new HashSet<String>();
            for (String sentinel : sentinels.split(";")) {
                sentinelSet.add(sentinel);
            }
            jedisSentinelPool = new JedisSentinelPool(masterName, sentinelSet,
                    RedisUtil.getPoolConfig("jedis-sentinel-pool"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static JedisSentinelPool getJedisSentinelPool() {
        return jedisSentinelPool;
    }

}
