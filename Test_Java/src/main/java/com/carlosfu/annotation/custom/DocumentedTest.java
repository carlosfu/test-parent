package com.carlosfu.annotation.custom;

/**
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
public class DocumentedTest {
    @DocumentedAnnotation(hello = "welcome")
    public void method() {
        System.out.println("hello world");
    }
}
