package com.carlosfu.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carlosfu.util.IShutdown;
import com.carlosfu.util.ObjectGenerator;
import com.carlosfu.util.ObjectHolder;

/**
 * Redis的单例
 * 
 * @author leifu
 * @Date 2014年11月6日
 * @Time 下午5:02:22
 */
public class RedisUtil implements IShutdown {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static RedisGenerator generator = new RedisGenerator();

    private RedisUtil(String name) {

    }

    public static RedisUtil getClient(String name) {
        return ObjectHolder.get(name, generator);
    }

    // Cache对象生成类
    private static class RedisGenerator implements ObjectGenerator<RedisUtil> {
        public RedisUtil generate(String name) {
            return new RedisUtil(name);
        }
    }

    @Override
    public void shutdown() {
        // 关闭资源操作,比如连接池等等
        // 本类关闭redis连接池等操作
    }

    public static void main(String[] args) {
        RedisUtil redis = RedisUtil.getClient("redis");
    }

}
