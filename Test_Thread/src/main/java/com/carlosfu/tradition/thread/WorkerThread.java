package com.carlosfu.tradition.thread;

import java.util.concurrent.TimeUnit;

import com.carlosfu.util.DateUtil;

/**
 * 工作，每秒休息一次
 * 
 * @author leifu
 * @Time 2014年9月10日
 */
public class WorkerThread extends Thread {
    private String name;

    private int workSeconds;

    public WorkerThread(String name, int workSeconds) {
        super();
        this.name = name;
        this.workSeconds = workSeconds;
    }

    @Override
    public void run() {
        while (workSeconds > 0) {
            System.out.println(DateUtil.getCurrentDateTime() + ": " + name + " is running.....");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            workSeconds--;
        }
    }

}
