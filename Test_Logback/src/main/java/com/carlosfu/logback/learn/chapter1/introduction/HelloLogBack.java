package com.carlosfu.logback.learn.chapter1.introduction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloLogBack {

	/**
	 * 如果不写配置文件，会用默认logger输出
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Logger logger = LoggerFactory
				.getLogger("com.carlosfu.logback.learn.chapter1.introduction.HelloLogBack");
		logger.debug("Hello world.");
	}
}
