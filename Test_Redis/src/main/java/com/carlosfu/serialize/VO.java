package com.carlosfu.serialize;

import java.io.Serializable;

/**
 * 
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午5:27:08
 * @param <T>
 */
public class VO<T> implements Serializable {

    private T value;

    public VO(T value) {
        this.value = value;
    }

    public VO() {
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "VO{" +
                "value=" + value +
                '}';
    }
}
