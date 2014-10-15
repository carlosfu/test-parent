package com.carlosfu.annotation.jdk;

import java.util.Date;

/**
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
public class DeprecatedTest {
    @Deprecated
    public void doSomething() {
        System.out.println("do something!");
    }

    public static void main(String[] args) {
        DeprecatedTest test = new DeprecatedTest();
        test.doSomething();

        Date date = new Date();
        System.out.println(date.toLocaleString());
    }
}
