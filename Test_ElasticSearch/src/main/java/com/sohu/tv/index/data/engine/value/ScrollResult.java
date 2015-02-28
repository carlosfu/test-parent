package com.sohu.tv.index.data.engine.value;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;

import java.util.List;
import java.util.Map;

/**
 * Created by yijunzhang on 15-2-15.
 */
public class ScrollResult {
    /**
     * 操作是否成功
     */
    private boolean isSuccess;

    /**
     * scrollId,第一次为空
     */
    private String scrollId;

    /**
     * timeValue 每次scroll默认1分钟
     */
    private TimeValue timeValue = TimeValue.timeValueMinutes(1);

    /**
     * 每次scroll量,默认:200
     */
    private int size = 200;

    /**
     * 当前scroll返回的数据
     */
    private List<Map<String, Object>> dataList;

    /**
     * 当前scroll返回的response对象
     */
    private SearchResponse scrollResponse;

    /**
     * 调用异常
     */
    private Exception exception;

    public ScrollResult(boolean isSuccess, String scrollId, List<Map<String, Object>> dataList, SearchResponse scrollResponse) {
        this.isSuccess = isSuccess;
        this.scrollId = scrollId;
        this.dataList = dataList;
        this.scrollResponse = scrollResponse;
    }

    public ScrollResult(boolean isSuccess, Exception exception) {
        this.isSuccess = isSuccess;
        this.exception = exception;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public TimeValue getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(TimeValue timeValue) {
        this.timeValue = timeValue;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public SearchResponse getScrollResponse() {
        return scrollResponse;
    }

    public void setScrollResponse(SearchResponse scrollResponse) {
        this.scrollResponse = scrollResponse;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
