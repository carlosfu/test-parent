package com.carlosfu.tradition.thread.interrupte;

class TestRunnable implements Runnable {
    public void run() {
        while (true)
        {
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis(); // 去系统时间的毫秒数
            while ((System.currentTimeMillis() - time < 1000)) {
                // 程序循环1秒钟，不同于sleep(1000)会阻塞进程。
            }
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        Runnable r = new TestRunnable();
        Thread th1 = new Thread(r);
        th1.start();
        th1.interrupt();
    }
}