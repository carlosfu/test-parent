package com.carlosfu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leifu
 * @Time 2014年9月10日
 */
public class DateUtil {
    /**
     * 返回当前时间 字符串
     * 
     * @return
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
