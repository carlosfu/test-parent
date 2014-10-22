package com.carlosfu.spring.jmx;

import java.util.Random;

/**
 * 数据层次
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午8:12:45
 */
public enum DataLevelEnum {
	HEAP("heap"),
	OFF_HEAP("offHeap"),
	REDIS("redis"),
	HBASE("hbase"),
	MYSQL("mysql");
	
	private String level;
	private DataLevelEnum(String level) {
		this.level = level;
	}
	public String getLevel() {
		return level;
	}

	/**
	 * 随机取一层
	 * @return
	 */
	public static DataLevelEnum getRandomLevel() {
		return DataLevelEnum.values()[new Random().nextInt(DataLevelEnum.values().length)];
	}
	
}
