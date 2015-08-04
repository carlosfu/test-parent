package com.carlosfu.tradition.fair.lock;

import java.util.concurrent.TimeUnit;

public class LockClass {
    
    private CustomRegularLock lock = new CustomRegularLock();

    public synchronized void methodSyn() throws InterruptedException {
        System.out.println("synchronized methodSyn sleep 3 seconds");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("synchronized methodSyn finish");
    }

    public synchronized void methodCustomLock() throws InterruptedException {
        lock.lock();
        System.out.println("customLock methodCustomLock sleep 3 seconds");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("customLock methodCustomLock finish");
        lock.unlock();
    }

}
