package com.carlosfu.sentinel.test;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carlosfu.redis.util.DigestUtils;
import com.carlosfu.redis.util.RedisSentinelFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Pipeline;
import redis.clients.util.SafeEncoder;

/**
 * sentinelTest
 * 
 * @author leifu
 * @Time 2014年9月5日
 */
public class SentinelTest {
    private static final Logger logger = LoggerFactory.getLogger(SentinelTest.class);

    public static Jedis jedis = null;

    public static String key = "99";
    public static String REGULAR_KEY_PREFIX = "carlosfu:hehe:regular:";
    public static String MAP_KEY_PREFIX = "carlosfu:hehe:map:";
    // 普通get set的key
    public static String regularRedisKey = REGULAR_KEY_PREFIX + DigestUtils.md5DigestAsHex(SafeEncoder.encode(key));
    // map的key
    public static String mapRedisKey = MAP_KEY_PREFIX + DigestUtils.md5DigestAsHex(SafeEncoder.encode(key));

    @BeforeClass
    public static void setUp() {
        JedisSentinelPool jedisSentinelPool = RedisSentinelFactory.getJedisSentinelPool();
        jedis = jedisSentinelPool.getResource();
    }

    @AfterClass
    public static void tearDown() {
        if (jedis != null)
            jedis.close();
    }

   /**
    * 普通set
    */
   @Test
   public void testDel() {
       try {
           try {
               Long returnLong = jedis.del(regularRedisKey);
               System.out.println("return long : " + returnLong);
           } catch (Exception e) {
               logger.error(e.getMessage(), e);
           }
       } catch (Exception e) {
           logger.error(e.getMessage(), e);
       }
   }
   
   @Test
   public void testGetStar() {
       try {
           try {
               Set<String> set = jedis.keys("drama:manual:recommend:*");
               System.out.println(set);
           } catch (Exception e) {
               logger.error(e.getMessage(), e);
           }
       } catch (Exception e) {
           logger.error(e.getMessage(), e);
       }
   }
   
   @Test
   public void testGetSome() {
       try {
           try {
               Map<String, String> set = jedis.hgetAll("drama:manual:recommend:62b7b149c301c129902ce79a94f4e3cd");
               System.out.println(set);
           } catch (Exception e) {
               logger.error(e.getMessage(), e);
           }
       } catch (Exception e) {
           logger.error(e.getMessage(), e);
       }
   }
   
   
   
    
    /**
     * 普通set
     */
    @Test
    public void testSet() {
        String value = "hello,world!";
        try {
            Pipeline pipeline = jedis.pipelined();
            try {
                System.out.println("regularRedisKey: " + regularRedisKey);
                pipeline.set(regularRedisKey, String.valueOf(value));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            pipeline.sync();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    /**
     * 普通set
     */
    @Test
    public void testSet2() {
        String value = "hello,world!";
        try {
            try {
                System.out.println("regularRedisKey: " + regularRedisKey);
                String returnResult = jedis.set(regularRedisKey, String.valueOf(value));
                System.out.println("returnResult: " + returnResult);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 普通get
     * @author leifu
     * @Time 2014年9月5日
     */
    @Test
    public void testGet() {
        try {
            Pipeline pipeline = jedis.pipelined();
            try {
                pipeline.get(regularRedisKey);
                List<Object> list = pipeline.syncAndReturnAll();
                for (Object o : list) {
                    System.out.println("obj: " + o);
                }
                System.out.println("list: " + list);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            pipeline.sync();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void testGet2() {
        try {
            try {
                String value = jedis.get(regularRedisKey);
                System.out.println("list: " + value);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 放HashMap
     */
    @Test
    public void testHSet() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("vid", "123");
        map.put("title", "playlistRec");
        try {
            Pipeline pipeline = jedis.pipelined();
            try {
                pipeline.hmset(MAP_KEY_PREFIX, map);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    
    /**
     * 获取hashmap
     */
    @Test
    public void testHGet() {
        try {
            Pipeline pipeline = jedis.pipelined();
            try {
                pipeline.hgetAll(MAP_KEY_PREFIX);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            List<Object> list = pipeline.syncAndReturnAll();
            System.out.println(list);
            for (Object o : list) {
                System.out.println(o);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
