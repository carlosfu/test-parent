package com.carlosfu.bigmemory.util;


import java.io.Serializable;

/**
 * 封装缓存返回结果
 * User: yijunzhang
 * Date: 13-11-14
 * Time: 下午2:35
 */
public class DataResult<T extends Serializable> {
    private DataStatusEnum status;

    private DataLevelEnum level;

    private T value;

    public DataStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DataStatusEnum status) {
        this.status = status;
    }

    public DataLevelEnum getLevel() {
        return level;
    }

    public void setLevel(DataLevelEnum level) {
        this.level = level;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return status != null && status.getIndex() == DataStatusEnum.SUCCESS.getIndex();
    }

    public DataResult(DataStatusEnum status, DataLevelEnum level, T value) {
        this.status = status;
        this.level = level;
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataResult{" +
                "status=" + status +
                ", level=" + level +
                ", value=" + value +
                '}';
    }
}
