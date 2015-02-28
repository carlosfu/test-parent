package com.sohu.tv.index.data.engine.value;

import org.elasticsearch.common.unit.TimeValue;

/**
 * Created by yijunzhang on 15-2-15.
 */
public class ScrollParam {

    /**
     * 索引名
     */
    private final String index;

    /**
     * 索引类型
     */
    private final String type;

    /**
     * scrollId,第一次为空
     */
    private String scrollId;

    /**
     * timeValue 每次scroll默认1分钟
     */
    private TimeValue timeValue = TimeValue.timeValueMinutes(1);

    /**
     * 每个分片获取的数量量,默认:200
     * 总数量等于:shared * size
     */
    private int size = 200;

    public ScrollParam(String index, String type, String scrollId) {
        this.index = index;
        this.type = type;
        this.scrollId = scrollId;
    }

    public ScrollParam(String index, String type, String scrollId, TimeValue timeValue, int size) {
        this.index = index;
        this.type = type;
        this.scrollId = scrollId;
        this.timeValue = timeValue;
        this.size = size;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public String getScrollId() {
        return scrollId;
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

    public String getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

}
