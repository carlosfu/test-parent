package com.carlosfu.serialize;

import java.io.Serializable;

/**
 * @author leifu(original: mobil)
 * @Time 2014-10-9
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
