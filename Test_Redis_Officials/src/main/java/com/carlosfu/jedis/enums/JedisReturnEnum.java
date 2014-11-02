package com.carlosfu.jedis.enums;

/**
 * jedis返回状态常量枚举
 * @author leifu
 * @Date 2014年11月2日
 * @Time 上午10:55:13
 */
public enum JedisReturnEnum {
	OK("OK"),
	FAIL("fail");
	
	private String value;

	private JedisReturnEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}
