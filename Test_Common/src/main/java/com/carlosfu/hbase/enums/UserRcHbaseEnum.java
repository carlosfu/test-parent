package com.carlosfu.hbase.enums;

/**
 * User: yijunzhang
 * Date: 13-11-25
 * Time: 下午6:02
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
