package com.carlosfu.redis.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yijunzhang on 14-8-20.
 */
public class RedisUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    public static GenericObjectPoolConfig getPoolConfig(String jmxNamePrefix) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 5);
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 2);
        //JedisPool.borrowObject最大等待时间
        poolConfig.setMaxWaitMillis(1000L);
        poolConfig.setJmxNamePrefix(jmxNamePrefix);
        poolConfig.setJmxEnabled(true);
        return poolConfig;
    }

    public static Properties getRedisProperties() {
        InputStream in = RedisUtil.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            in.close();
        } catch (Exception e) {
            LOGGER.error("load redis.properties error->" + e.getMessage(), e);
        }
        return properties;
    }

}
