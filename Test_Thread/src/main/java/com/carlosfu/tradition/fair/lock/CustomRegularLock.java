package com.carlosfu.tradition.fair.lock;

/**
 * 自定义简单锁
 * 
 * @author leifu
 * @Time 2014年9月26日
 */
public class CustomRegularLock {
    private Thread currentThread;

    private volatile boolean isLock = false;

    public synchronized void lock() throws InterruptedException {
        while (isLock) {
            this.wait();
        }
        //此时锁没有被获取,所以这个线程isLock
        isLock = true;
        currentThread = Thread.currentThread();
    }

    public synchronized void unlock() {
        if (!Thread.currentThread().equals(currentThread)) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLock = false;
        currentThread = null;
        this.notify();
    }
}
