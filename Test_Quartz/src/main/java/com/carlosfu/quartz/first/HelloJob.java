package com.carlosfu.quartz.first;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {
    private Logger logger = LoggerFactory.getLogger(HelloJob.class);

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        logger.info("Hello World! - " + new Date());
    }
}