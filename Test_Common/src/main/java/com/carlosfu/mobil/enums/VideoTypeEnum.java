package com.carlosfu.mobil.enums;

/**
 * 视频/专辑类型枚举
 * User: yijunzhang
 * Date: 13-11-26
 * Time: 下午4:29
 */
public enum VideoTypeEnum {
    //专辑
    P_ID("0"),
    //视频
    TV_ID("1"),
    //高清视频/标清
    VER_ID("2"),
    //频道
    CATECODE("3"),
    //只是简单的储存键值对.key为字符串放到rowkey上
    KEY("4"),
    /** 等级 */
    LEVEL("5"),
    /** 关键词 */
    KEYWORD_ID("6"),
    /** 关键词分类id */
    CATEGORY_ID("7"),
    /** 主题代码 */
    TOPIC_CODE("8");

    private String value;

    private VideoTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
