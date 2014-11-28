package com.carlosfu.quartz.first;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExample {
    private Logger logger = LoggerFactory.getLogger(SimpleExample.class);

    public static void main(String[] args) throws Exception {
        SimpleExample example = new SimpleExample();
        example.run();
    }

    public void run() throws Exception {
        // 1.定义调度器
        logger.info("----------------- Initializing ----------------------");
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        logger.info("----------------- Initialization Complete -----------");

        // 2.定义job and trigger
        // 去掉秒，分钟
        Date runTime = DateBuilder.evenMinuteDate(new Date());
        logger.info("----------------- Scheduling Job:{}  -------------------", runTime);
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

        // 3.启动trigger和job
        sched.scheduleJob(job, trigger);
        logger.info(job.getKey() + " will run at: " + runTime);
        sched.start();
        logger.info("----------------- Started Scheduler -----------------");
        logger.info("----------------- Waiting 65 seconds... -------------");

        // 4.休息一分钟在关闭调度器
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        // 5.关闭
        logger.info("----------------- Shutting Down ---------------------");
        sched.shutdown(true);
        logger.info("----------------- Shutdown Complete -----------------");
    }

}