package com.carlosfu.tradition.thread.interrupte;

class TestRunnable2 implements Runnable {
    public void run() {
        try {
            Thread.sleep(10000); // 这个线程将被阻塞1000秒
        } catch (InterruptedException e) {
            e.printStackTrace();
            // do more work and return.
        }
    }
}

public class TestDemo2 {
    public static void main(String[] args) {
        Runnable tr = new TestRunnable2();
        Thread th1 = new Thread(tr);
        th1.start(); // 开始执行分线程
        while (true) {
            th1.interrupt(); // 中断这个分线程
        }
    }
}