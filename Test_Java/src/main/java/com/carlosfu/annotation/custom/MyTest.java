package com.carlosfu.annotation.custom;

/**
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
@MyAnnotation(hello = "beijing", world = "shanghai")
public class MyTest {
    @MyAnnotation(hello = "tianjin", world = "shangdi")
    @Deprecated
    @SuppressWarnings("unchecked")
    public void output() {
        System.out.println("output something!");
    }
}
