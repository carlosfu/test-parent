package com.carlosfu.annotation.jdk;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
public class SuppressWarningsTest
{
    @SuppressWarnings({"unchecked", "deprecation"})
    public static void main(String[] args)
    {
        Map map = new TreeMap();
        map.put("hello", new Date());
        System.out.println(map.get("hello"));
        
        Date date = new Date();
        System.out.println(date.toLocaleString());
    }
}
