package com.carlosfu.logback.error.crawl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carlosfu.logback.enums.LogLevel;

public class ErrorObject {
    /**
     * logback作者认为不需要用static,已经做了优化
     */
    private Logger logger = LoggerFactory.getLogger(ErrorObject.class);

    public void errorMethod(LogLevel level) {
        try {
            String a = null;
            a.split(",");
        } catch (Exception e) {
            LogLevel.print(logger,level, e);
        }
    }

    public static void main(String[] args) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.errorMethod(LogLevel.INFO);
    }

}
