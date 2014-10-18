package com.carlosfu.my.logback.level.auto;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logback能不重启，修改日志级别
 * 
 * @author leifu
 * @Time 2014年8月22日
 */
public class TestLogBackLevelAuto {
    private final static Logger logger = LoggerFactory.getLogger(TestLogBackLevelAuto.class);

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            String value = System.getProperty("java.awt.headless");
            logger.info("=============info message:{}=============", value);
            logger.debug("=============debug message:{}=============", value);
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
