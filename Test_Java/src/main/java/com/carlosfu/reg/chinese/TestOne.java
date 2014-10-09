package com.carlosfu.reg.chinese;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestOne {

    @Test
    public void testFirst() {
        Pattern p = Pattern.compile("更新至(\\s*|第)(\\d+)集");
        Matcher m = p.matcher("更新至661集");
        while (m.find()) {
            assertEquals("661", m.group(2));
        }
    }

    @Test
    public void testFirstV2() {
        Pattern p = Pattern.compile("更新至(\\s*|第)(\\d+)集");
        Matcher m = p.matcher("更新至第661集");
        while (m.find()) {
            assertEquals("661", m.group(2));
        }
    }
    
    @Test
    public void testTwo() {
        Pattern p = Pattern.compile("[\u4E00-\u9FA5]*第(\\d+)集");
        Matcher m = p.matcher("匆匆那年第14集");
        while (m.find()) {
            assertEquals("14", m.group(1));
        }
    }
    
    @Test
    public void testTwoV2() {
        Pattern p = Pattern.compile("[\u4E00-\u9FA5]*第(\\d+)集");
        Matcher m = p.matcher("第19集");
        while (m.find()) {
            assertEquals("19", m.group(1));
        }
    }
    
    public void testThree(){
        String pattern = "vid:(\\w+), pid:(\\w+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher("vid:1185234, pid:71069-1218");
        while (m.find()) {
            String str1 = m.group(1);
            String str2 = m.group(2);
            try {
                Integer.parseInt(str2);
            } catch (Exception e) {
                System.out.println(str1);
            }
        }
    }
    
    
    
}
