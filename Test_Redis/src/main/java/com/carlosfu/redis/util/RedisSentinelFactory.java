package com.carlosfu.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by yijunzhang on 14-8-20.
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
