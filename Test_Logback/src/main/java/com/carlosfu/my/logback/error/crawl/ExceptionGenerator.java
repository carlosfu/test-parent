package com.carlosfu.my.logback.error.crawl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carlosfu.my.logback.enums.LogLevel;

public class ExceptionGenerator {
	/**
	 * logback作者认为不需要用static,已经做了优化
	 */
	private Logger logger = LoggerFactory.getLogger(ExceptionGenerator.class);

	public void nullPointerExceptionMethod(LogLevel level) {
		try {
			String a = null;
			a.split(",");
		} catch (Exception e) {
			LogLevel.print(logger, level, e);
		}
	}

	public void fileReaderException(LogLevel level) {
		try {
			new BufferedReader(new FileReader(new File("heheh.ttt")));
		} catch (FileNotFoundException e) {
			LogLevel.print(logger, level, e);
		}
	}

	public static void main(String[] args) {
		ExceptionGenerator errorObject = new ExceptionGenerator();
		errorObject.nullPointerExceptionMethod(LogLevel.INFO);
	}

}
