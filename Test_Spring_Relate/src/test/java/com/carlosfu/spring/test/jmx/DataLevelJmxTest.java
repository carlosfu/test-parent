package com.carlosfu.spring.test.jmx;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;

import com.carlosfu.spring.jmx.DataLevelEnum;
import com.carlosfu.spring.jmx.DataLevelWatcher;
import com.carlosfu.spring.test.base.BaseTest;

/**
 * 测试spring JMX
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午9:16:39
 */
public class DataLevelJmxTest extends BaseTest{
	
	@Resource(name="dataLevelWatcher")
	private DataLevelWatcher dataLevelWatcher;
	
	@Test
	public void testSpringJmx() throws InterruptedException{
		while (true) {
			dataLevelWatcher.increment(DataLevelEnum.getRandomLevel());
			TimeUnit.MILLISECONDS.sleep(20);
		}
	}
}