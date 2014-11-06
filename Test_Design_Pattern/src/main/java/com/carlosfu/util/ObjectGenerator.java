package com.carlosfu.util;

/**
 * 对象生成器对象生成器
 * @author leifu
 * @Date 2014年11月6日
 * @Time 下午5:10:17
 * @param <T>
 */
public interface ObjectGenerator<T> {
    public T generate(String name);
}