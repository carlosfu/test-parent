package com.carlosfu.bigmemory.util;

/**
 * 数据操作返回状态
 * User: yijunzhang
 * Date: 13-11-14
 * Time: 下午2:40
 */
public enum DataStatusEnum {
    SUCCESS(0, "success"),
    NOT_EXIST(3, "not_exist"),
    ALREADY_EXISTS(4,"already_exist"),
    TIME_OUT(6, "time_out"),
    ARGS_ERROR(9, "args_error"),
    INNER_ERROR(12, "inner_error"),
    UN_SUPPORTED(15,"un_supported");

    private int index;

    private String name;

    private DataStatusEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
