package com.carlosfu.cluster.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.carlosfu.redis.util.RedisClusterFactory;

import redis.clients.jedis.PipelineCluster;

public class ClusterTest {
    private static PipelineCluster redisCluster;

    private static String BASE_PREFIX_KEY = "carlosfu:reids:cluster:list:";

    @BeforeClass
    public static void setUp() {
        redisCluster = RedisClusterFactory.getRedisCluster();
    }

    @AfterClass
    public static void tearDown() {
    }

    @Test
    public void testSetAndGetAndDel() {
        String key = BASE_PREFIX_KEY + "hello";
        String response = redisCluster.set(key, "hehe world");
        System.out.println("response: " + response);
        // 2.获取
        String value = redisCluster.get(key);
        System.out.println("before del value: " + value);
        // 3.删除
        Long delResult = redisCluster.del(key);
        System.out.println("delResult: " + delResult);
        // 4.删除后获取
        System.out.println("after del value: " + redisCluster.get(key));

    }

}
