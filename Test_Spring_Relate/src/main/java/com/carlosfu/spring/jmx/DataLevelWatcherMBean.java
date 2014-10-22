package com.carlosfu.spring.jmx;

import java.util.List;

/**
 * 数据层调用统计模拟
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午8:13:02
 */
public interface DataLevelWatcherMBean {
	
	/**
	 * 如果用get相当于属性
	 * @return
	 */
	public List<String> getLevelList();
	
	/**
	 * 获取各数据层的调用量
	 */
	public List<String> showLevelList();

	/**
	 * 获取某一数据层的调用量
	 * @param level
	 */
	public Long getSomeLevel(String level);
	
	/**
	 * 某个数据层调用量自增
	 * @param cacheLevel
	 */
	public void increment(DataLevelEnum cacheLevel);
}
