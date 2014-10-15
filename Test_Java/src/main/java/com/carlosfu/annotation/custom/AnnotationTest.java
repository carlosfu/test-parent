package com.carlosfu.annotation.custom;

/**
 * 默认是个标识注解, Marker Annotation
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
public @interface AnnotationTest {
    String[] value1() default "hello";
    EnumTest value2();
}

enum EnumTest {
    Hello, World, Welcome;
}
