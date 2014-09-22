package com.carlosfu.tradition.join.test;

import org.junit.Test;

import com.carlosfu.tradition.thread.WorkerThread;
import com.carlosfu.util.DateUtil;

/**
 * join测试
 * 
 * @author leifu
 * @Time 2014年9月10日
 */
public class JoinTest {
    @Test
    public void testJoinUtilThreadSop() throws InterruptedException {
        Thread workerThread = new WorkerThread("ronaldo", 3);
        workerThread.start();

        // 一直等到workerThread结束
        workerThread.join();
        // 同workerThread.join(0);

        System.out.println(DateUtil.getCurrentDateTime() + ": main Thread finish......");
    }

    @Test
    public void testJoinLimitTime() throws InterruptedException {
        Thread workerThread = new WorkerThread("ronaldo", 4);
        workerThread.start();

        // 如果workerThread在2秒后还没执行完成，继续执行主线程, 同时会中断workerThread
        workerThread.join(2000);

        System.out.println(DateUtil.getCurrentDateTime() + ": main Thread finish......");
    }

}
