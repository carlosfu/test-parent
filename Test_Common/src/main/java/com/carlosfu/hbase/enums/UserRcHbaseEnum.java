package com.carlosfu.hbase.enums;

/**
 * hbase用户相关表(来自投放)
 * @author leifu
 * @Date 2014年10月22日
 * @Time 下午5:32:10
 */
public enum UserRcHbaseEnum {
    TABLE("rc_entire"),
    TABLE_IFOX("rc_entire_ifox"),
    FAMILY("f");

    private String value;

    private UserRcHbaseEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
