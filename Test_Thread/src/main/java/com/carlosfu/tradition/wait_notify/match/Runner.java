package com.carlosfu.tradition.wait_notify.match;

import java.util.concurrent.TimeUnit;

/**
 * 运动员
 * 
 * @author leifu
 * @Time 2014年9月18日
 */
public class Runner {
    private Starter starter;

    public void run() throws InterruptedException {
        System.out.println("============runner:等待枪声================");

        // 等待starter的notify
        synchronized (starter) {
            starter.wait();
        }
        System.out.println("=============runner: 运动员听到枪声，开始跑了===========");
        System.out.println("=============runner: 运动员预计跑3秒==========");
        System.out.println("=============runner: 运动员跑完结束===============");
    }

    public Starter getStarter() {
        return starter;
    }

    public Runner(Starter starter) {
        this.starter = starter;
    }
}
