package com.carlosfu.annotation.custom;

/**
 * 
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
public class MyTargetTest {
    @MyTarget("hello")
    public void doSomething() {
        System.out.println("hello world");
    }
}
