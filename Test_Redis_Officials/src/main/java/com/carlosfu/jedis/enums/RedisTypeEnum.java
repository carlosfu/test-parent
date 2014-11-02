package com.carlosfu.jedis.enums;

/**
 * redis类型
 * @author leifu
 * @Date 2014年11月2日
 * @Time 上午10:58:09
 */
public enum RedisTypeEnum {
	REDIS_STANDALONE("redis_standalone"),
	REDIS_SENTINEL("redis_sentinel"),
	REDIS_CLUSTER("redis_cluster");
	
	private String value;

	private RedisTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}
