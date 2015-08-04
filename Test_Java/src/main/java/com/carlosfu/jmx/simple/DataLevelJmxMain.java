package com.carlosfu.jmx.simple;

import java.lang.management.*;
import java.util.concurrent.TimeUnit;

import javax.management.*;

/**
 * 测试JMX
 * @author leifu
 * @Time 2014-10-20
 */
public class DataLevelJmxMain {
	public static void main(String[] args) throws Exception {
		// 1. Get the Platform MBean Server
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		// 2. Construct the ObjectName for the MBean we will register
		ObjectName name = new ObjectName("com.carlosfu.jmx.simple.mbeans:type=DataLevelWatcher");

		// 3. Create the Hello World MBean
		DataLevelWatcher mbean = new DataLevelWatcher();

		// 4. Register the Hello World MBean
		mbs.registerMBean(mbean, name);

		while (true) {
			mbean.increment(DataLevelEnum.getRandomLevel());
			TimeUnit.MILLISECONDS.sleep(20);
		}
	}
}