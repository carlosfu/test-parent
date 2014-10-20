package com.carlosfu.jmx.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.common.util.concurrent.AtomicLongMap;

/**
 * 数据层统计实现类，名字必须是接口去掉MBean
 * @author leifu
 * @Time 2014-10-20
 */
public class DataLevelWatcher implements DataLevelWatcherMBean {
	/**
	 * 统计用的map
	 */
	private AtomicLongMap<String> statisticMap = AtomicLongMap.create();

	@Override
	public List<String> showLevelList() {
		if (statisticMap.isEmpty()) {
            return new ArrayList<String>();
        }
        List<String> result = new ArrayList<String>();
        for (Entry<String, Long> entry : statisticMap.asMap().entrySet()) {
            result.add(entry.getKey() + ":" + entry.getValue());

        }
        return result;
	}

	@Override
	public Long getSomeLevel(String level) {
		return statisticMap.get(level);
	}

	@Override
	public void increment(DataLevelEnum cacheLevel) {
		statisticMap.incrementAndGet(cacheLevel.getLevel());
	}

	@Override
	public List<String> getLevelList() {
		return showLevelList();
	}

}