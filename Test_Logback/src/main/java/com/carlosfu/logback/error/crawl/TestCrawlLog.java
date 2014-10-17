package com.carlosfu.logback.error.crawl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

import com.carlosfu.logback.enums.LogLevel;

/**
 * 收集异常日志
 * @author leifu
 * @Time 2014年10月17日
 */
public class TestCrawlLog {
	
	/**
	 * 产生空指针和文件空指针的对象
	 */
	private ExceptionGenerator errorObject = new ExceptionGenerator();

	/**
	 * 收集器，继承AppenderBase
	 */
	private ErrorStatisticsAppender errorStatisticsAppender = new ErrorStatisticsAppender();

	/**
	 * rootLogger添加自定义appende，这样就可以收集异常信息
	 */
	public void initLoggerFactory() {
		// 收集异常
		LoggerContext loggerContext = (LoggerContext) LoggerFactory
				.getILoggerFactory();
		Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
		errorStatisticsAppender.setContext(loggerContext);
		errorStatisticsAppender.start();
		rootLogger.addAppender(errorStatisticsAppender);
	}

	/**
	 * 制造异常
	 * @param times
	 */
	public void generateException(int times) {
		int realErrorCount = 0;
		while (realErrorCount < times) {
			int random = new Random().nextInt(100);
			if (random % 3 == 0) {
				errorObject.nullPointerExceptionMethod(LogLevel.ERROR);
			} else {
				errorObject.fileReaderException(LogLevel.ERROR);
			}
			realErrorCount++;
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 打印异常名和个数的Map结果
	 */
	public void printExceptionNameCountMap() {
		System.out.println(ErrorStatisticsAppender.ERROR_NAME_VALUE_MAP);
	}

	public static void main(String[] args) throws InterruptedException {
		TestCrawlLog test = new TestCrawlLog();
		test.initLoggerFactory();
		test.generateException(120);
		test.printExceptionNameCountMap();
	}

}
