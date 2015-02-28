package com.sohu.tv.index.data.engine.value;

/**
 * Created by yijunzhang on 15-2-2.
 */
public class Result<T> {

    /**
     * 操作是否成功
     */
    private boolean isSuccess;

    private T value;

    private Exception exception;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Result(boolean isSuccess, T value) {
        this.isSuccess = isSuccess;
        this.value = value;
    }

    public Result(boolean isSuccess, Exception exception) {
        this.isSuccess = isSuccess;
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Result [isSuccess=" + isSuccess + ", value=" + value + ", exception=" + exception + "]";
    }
    
    
}
