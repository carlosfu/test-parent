package com.carlosfu.util;

/**
 * 在Test_Java和Test_Common同时引入com.carlosfu.util.TestUtil
 * intellij
 * 来测试如果一个工程包含了完全相同class路径的多个类，会出现怎么样的效果
 * 
 * @author leifu
 * @Time 2014年9月29日
 */
public class TestUtil {
    public static void hello() {
        System.out.println("Test_Common says hello world");
    }
}
