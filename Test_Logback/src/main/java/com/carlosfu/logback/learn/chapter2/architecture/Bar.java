package com.carlosfu.logback.learn.chapter2.architecture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bar {
	private Logger logger = LoggerFactory.getLogger(Bar.class);

	public void doIt() {
		logger.debug("doing my job");
	}
}