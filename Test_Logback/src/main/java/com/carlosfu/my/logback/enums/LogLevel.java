package com.carlosfu.my.logback.enums;

import org.slf4j.Logger;

/**
 * 日志级别
 * 
 * @author leifu
 * @Time 2014年10月17日
 */
public enum LogLevel {

    ERROR("error"),
    WARN("warn"),
    INFO("info"),
    DEBUG("debug");

    String value;

    private LogLevel(String value) {
        this.value = value;
    }

    public String getvalue() {
        return value;
    }

    public static void print(Logger logger, LogLevel level, Exception e) {
        if (ERROR.equals(level)) {
            logger.error(e.getMessage(), e);
        } else if (WARN.equals(level)) {
            logger.warn(e.getMessage(), e);
        } else if (INFO.equals(level)) {
            logger.info(e.getMessage(), e);
        } else if (DEBUG.equals(level)) {
            logger.debug(e.getMessage(), e);
        }
    }

}
