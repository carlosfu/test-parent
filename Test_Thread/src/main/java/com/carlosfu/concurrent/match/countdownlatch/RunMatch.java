package com.carlosfu.concurrent.match.countdownlatch;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 一场跑步比赛: 3个人参加，等待发令员法令，比赛开始，最终统计比赛结果
 * 
 * @author leifu
 * @Time 2014年8月13日
 */
public class RunMatch {
    public static void main(String[] args) {
        //参赛人数
        int runnerNum = 3;
        
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //发令员只有一个
        final CountDownLatch orderLatch = new CountDownLatch(1);
        final CountDownLatch runnerLatch = new CountDownLatch(runnerNum);

        for (int i = 0; i < runnerNum; i++) {
            pool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + ", 准备比赛就绪,等待发令枪声!");
                        //等待指令
                        orderLatch.await();
                        System.out.println(Thread.currentThread().getName() + " at " + (new Date()) + " 接受到指令");
                        //模拟跑步时间
                        long sleepTime = (long) (Math.random() * 3000);
                        Thread.sleep(sleepTime);
                        System.out.println(Thread.currentThread().getName() + " at " + (new Date()) + " 完成比赛");
                        // 跑完了通知发令员，其实就是主线程
                        runnerLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            //模拟发令员（主线程）也有做一些事情
            Thread.sleep(3000);
            System.out.println("main 发令员发起枪声，开始比赛!!");
            //通知开始比赛
            orderLatch.countDown();
            // 等待比赛结束
            runnerLatch.await();
            System.out.println("比赛结束了");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        pool.shutdown();
    }
}
