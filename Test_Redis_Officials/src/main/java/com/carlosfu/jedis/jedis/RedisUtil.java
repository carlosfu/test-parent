package com.carlosfu.jedis.jedis;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carlosfu.util.ConfigLoadUtil;
import com.carlosfu.util.IShutdown;
import com.carlosfu.util.ObjectHolder;
import com.carlosfu.util.ObjectHolder.ObjectGenerator;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

/**
 * redis客户端
 * @author leifu(参考count)
 * @Date 2014年11月2日
 * @Time 上午10:52:53
 */
public class RedisUtil implements IShutdown{

	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
	private Pool<Jedis> pool;

	private String clientName;
	
	private static final String DEFAULT_CONFIG_NAME = "redis";
	
	public static final String CONFIG_PROPERTIES= ".properties";
	
	private RedisUtil(String name) {
		logger.info("init " + name);
		JedisPoolConfig config = new JedisPoolConfig();
		Properties properties = ConfigLoadUtil.loadConnectString(name+CONFIG_PROPERTIES, this.getClass());
		if (properties != null) {
			try {
				config.setMaxTotal(Integer.parseInt(properties
						.getProperty("maxTotal")));
				config.setMaxIdle(Integer.parseInt(properties
						.getProperty("maxIdle")));
				config.setMaxWaitMillis(Integer.parseInt(properties
						.getProperty("maxWaitMillis")));
				config.setTestOnBorrow(Boolean.parseBoolean(properties
						.getProperty("testOnBorrow")));
				config.setTestOnReturn(Boolean.parseBoolean(properties
						.getProperty("testOnReturn")));
				config.setTestWhileIdle(Boolean.parseBoolean(properties
						.getProperty("testWhileIdle")));
				config.setNumTestsPerEvictionRun(Integer.parseInt(properties
						.getProperty("numTestsPerEvictionRun")));
				config.setTimeBetweenEvictionRunsMillis(Integer
						.parseInt(properties.getProperty("timeBetweenEvictionRunsMillis")));// 一分钟
				config.setMinEvictableIdleTimeMillis(Integer
						.parseInt(properties.getProperty("minEvictableIdleTimeMillis")));
				
				String jmxNamePrefix = properties.getProperty("jmxNamePrefix");
				if(jmxNamePrefix != null) {
					config.setJmxEnabled(true);
					config.setJmxNamePrefix(jmxNamePrefix);
				}
				
				String model = properties.getProperty("model");
				String address = properties.getProperty("address");
				clientName = name+"-"+model+"-"+address;
				
				if("sentinel".equals(model)) {
					String[] tmp = address.split(",");
					Set<String> sentinelSet = new HashSet<String>();
					for(String addr : tmp) {
						sentinelSet.add(addr);
					}
					String masterName = properties.getProperty("masterName");
					pool = new JedisSentinelPool(masterName, sentinelSet, config);
				}else {
					String[] tmp = address.split(":");
					pool = new JedisPool(config, tmp[0],
							Integer.parseInt(tmp[1]));
				}
				logger.info("jedis init {}", clientName);
			} catch (Exception e) {
				logger.error("jedis init error", e);
			}
		}
	}
	
	public static RedisUtil getClient(String name) {
		return ObjectHolder.get(name, generator);
	}
	
	public static RedisUtil getClient() {
		return ObjectHolder.get(DEFAULT_CONFIG_NAME, generator);
	}
	
	private static RedisGenerator generator = new RedisGenerator();
	//Cache对象生成类
	private static class RedisGenerator implements ObjectGenerator<RedisUtil>{
		public RedisUtil generate(String name) {
			return new RedisUtil(name);
		}
	}
	@Override
	public void shutdown() {
		try {
			pool.destroy();
			logger.info(clientName+" destroy");
		} catch (Exception e) {
			logger.error(clientName+" shutdown error", e);
		}
	}

	public Jedis getJedis() {
		try {
			return pool.getResource();
		} catch (Exception e) {
			logger.error("Pool.getResource error", e);
		}
		return null;
	}

	public void returnJedis(Jedis jedis) {
		pool.returnResource(jedis);
	}
	
	public void returnJedisWithException(Jedis jedis) {
		if(jedis == null) {
			return;
		}
		//ping需要catch起来，否则如果jedis链接真的有问题，可能会抛出java.util.ArrayList cannot be cast to [B，从而不会释放这个链接
		String ping = null;
		try {
			ping = jedis.ping();
		} catch (Exception e) {
			logger.error("ping error", e);
		}
		if("PONG".equals(ping)) {
			pool.returnResource(jedis);
		}else {
			logger.error("return broken resource {},{}", jedis, ping);
			pool.returnBrokenResource(jedis);
		}
	}


	public void set(String key, String val) {
		Jedis jedis = getJedis();
		try {
			jedis.set(key, val);
			returnJedis(jedis);
		} catch (Exception e) {
			returnJedisWithException(jedis);
			logger.error("set error "+key+":"+val, e);
		}
	}
	
	public Boolean exist(String key) {
		Jedis jedis = getJedis();
		try {
			Boolean rst = jedis.exists(key);
			returnJedis(jedis);
			return rst;
		} catch (Exception e) {
			returnJedisWithException(jedis);
			logger.error("set error "+key, e);
		}
		return false;
	}
	
	public String get(String key) {
		Jedis jedis = getJedis();
		try {
			String v = jedis.get(key);
			returnJedis(jedis);
			return v;
		} catch (Exception e) {
			returnJedisWithException(jedis);
			logger.error("get error "+key, e);
		}
		return null;
	}
	
	
}
