package com.carlosfu.bigmemory.enums;

/**
 * 数据服务返回出处
 * @author leifu(original: mobil)
 * @Time 2014-10-9
 */
public enum DataLevelEnum {
    LOCALCACHE(1, "localCache"),
    MEMCLOUD(3, "memcloud"),
    HBASE(6, "hbase"),
    API(9, "api"),
    DATASERVICE(12,"dataservice"),
    ZOOKEEPER(15,"zookeeper");

    private int index;

    private String value;

    private DataLevelEnum(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}
