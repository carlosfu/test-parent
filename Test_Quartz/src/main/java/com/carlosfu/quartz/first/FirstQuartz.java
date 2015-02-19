package com.carlosfu.quartz.first;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * First Quartz
 * 
 * @author leifu
 * @Date 2014年11月27日
 * @Time 上午10:12:20
 */
public class FirstQuartz {
    private final static Logger logger = LoggerFactory.getLogger(FirstQuartz.class);

    public static void main(String[] args) {
        try {
            // 声明默认的调度器
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler(); 
            // 开启调度器
            scheduler.start();
            // 关闭调度器
            scheduler.shutdown();
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }
}