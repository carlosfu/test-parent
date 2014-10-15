package com.carlosfu.annotation.custom;

/**
 * 
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface MyAnnotation {
    String hello() default "shengsiyuan";

    String world();
}
