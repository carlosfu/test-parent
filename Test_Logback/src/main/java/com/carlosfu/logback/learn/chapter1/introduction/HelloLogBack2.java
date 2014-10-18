package com.carlosfu.logback.learn.chapter1.introduction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class HelloLogBack2 {

	public static void main(String[] args) {
		Logger logger = LoggerFactory
				.getLogger("com.carlosfu.logback.learn.chapter1.introduction.HelloLogBack2");
		logger.debug("Hello world.");

		/**
		 * 输出内部执行过程
		 */
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
	}
}
