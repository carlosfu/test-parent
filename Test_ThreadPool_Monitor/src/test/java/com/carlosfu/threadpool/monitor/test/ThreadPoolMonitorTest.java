package com.carlosfu.threadpool.monitor.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolMonitorTest {
    private static Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        executorService.submit(thread);

        while (true) {
            
        }

    }

}
