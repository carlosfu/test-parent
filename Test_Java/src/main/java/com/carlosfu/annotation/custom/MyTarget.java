package com.carlosfu.annotation.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
@Target(ElementType.METHOD)
public @interface MyTarget {
    String value();
}
