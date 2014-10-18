package com.carlosfu.my.logback.error.crawl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.AppenderBase;
import com.google.common.util.concurrent.AtomicLongMap;

/**
 * 
 * @author leifu
 * @Time 2014年10月17日
 */
public class ErrorStatisticsAppender extends AppenderBase<ILoggingEvent> {

    public static final AtomicLongMap<String> ERROR_NAME_VALUE_MAP = AtomicLongMap.create();

    @Override
    protected void append(ILoggingEvent event) {
        if (event == null) {
            return;
        }
        if (event.getLevel() == Level.ERROR || event.getLevel() == Level.WARN) {
            IThrowableProxy throwableProxy = event.getThrowableProxy();
            if (throwableProxy != null) {
                String errorClassName = throwableProxy.getClassName();
                if (errorClassName != null && !"".equals(errorClassName.trim())) {
                    ERROR_NAME_VALUE_MAP.getAndIncrement(errorClassName);
                }
            }
        }

    }
}
