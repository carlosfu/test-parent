package com.carlosfu.redis.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.PipelineCluster;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * redis集群工厂类（来自投放项目）
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午5:28:26
 */
public class RedisClusterFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClusterFactory.class);

    private static PipelineCluster redisCluster;

    static {
        InputStream in = RedisClusterFactory.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
            String hosts = properties.getProperty("redis.cluster.hosts");
            int timeout = parseInt(properties.getProperty("redis.cluster.timeout"), 1);
            int maxRedirections = parseInt(properties.getProperty("redis.cluster.maxRedirections"), 5);
            String[] hostAndPorts = hosts.split(" ");
            for (String hostAndPort : hostAndPorts) {
                String[] args = hostAndPort.split(":");
                jedisClusterNodes.add(new HostAndPort(args[0], parseInt(args[1], 0)));
            }

            redisCluster = new PipelineCluster(RedisUtil.getPoolConfig("jedis-cluster-pool"), jedisClusterNodes, timeout, maxRedirections);
            in.close();
        } catch (Exception e) {
            LOGGER.error("load redis.properties error->" + e.getMessage(), e);
        }
    }

    public static int parseInt(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static PipelineCluster getRedisCluster() {
        return redisCluster;
    }
}
