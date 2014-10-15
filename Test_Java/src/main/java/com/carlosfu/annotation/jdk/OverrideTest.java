package com.carlosfu.annotation.jdk;

/**
 * 
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
public class OverrideTest {
    @Override
    public String toString() {
        return "This is OverrideTest";
    }

    public static void main(String[] args) {
        OverrideTest test = new OverrideTest();
        System.out.println(test);
    }
}
