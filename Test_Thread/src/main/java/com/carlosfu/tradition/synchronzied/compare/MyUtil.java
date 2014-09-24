package com.carlosfu.tradition.synchronzied.compare;

import java.util.concurrent.TimeUnit;

/**
 * 两个静态方法用synchronized, 一个普通方法用synchronized
 * 
 * @author leifu
 * @Time 2014年9月18日
 */
public class MyUtil {
    /**
     * 静态方法加方法锁1
     * 
     * @param second
     */
    public synchronized static void workStatic(int second) {
        System.out.println("============workStatic work " + second + " seconds==============");
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("============workStatic finish working==============");
    }

    /**
     * 静态方法加方法锁2
     * 
     * @param second
     */
    public synchronized static void workStatic2(int second) {
        System.out.println("============workStatic222 work " + second + " seconds==============");
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("============workStatic222 finish working==============");
    }

    /**
     * 普通方法加方法锁
     * 
     * @param second
     */
    public synchronized void workRegular(int second) {
        System.out.println("============workRegular work " + second + " seconds==============");
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("============workRegular finish working==============");
    }

}
