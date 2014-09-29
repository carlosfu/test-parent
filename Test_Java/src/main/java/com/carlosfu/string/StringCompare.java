package com.carlosfu.string;

import org.junit.Test;

/**
 * String比较月经贴。。
 * 
 * @author leifu
 * @Time 2014年9月29日
 */
public class StringCompare {
    @Test
    public void test1() {
        // 编译期, 常量池
        String s1 = "Monday";
        String s2 = "Monday";
        if (s1 == s2)
            System.out.println("s1 == s2");// right
        else {
            System.out.println("s1 != s2");
        }
    }

    @Test
    public void test2() {
        // 编译期，常量池
        String s1 = "Monday";
        // 运行期,堆
        String s2 = new String("Monday");
        if (s1 == s2) {
            System.out.println("s1 == s2");
        } else {
            System.out.println("s1 != s2"); // right
        }
        if (s1.equals(s2)) {
            System.out.println("s1 equals s2");// right
        } else {
            System.out.println("s1 not equals s2");
        }
    }

    @Test
    public void test3() {
        String s1 = "Monday";
        String s2 = new String("Monday");
        // 强制到常量池
        s2 = s2.intern();
        if (s1 == s2) {
            System.out.println("s1 == s2"); // right
        } else {
            System.out.println("s1 != s2");
        }
        if (s1.equals(s2)) {
            System.out.println("s1 equals s2"); // right
        } else {
            System.out.println("s1 not equals s2");
        }
    }

    @Test
    public void test4() {
        String s = "HELLO";
        String t = s.toUpperCase();
        if (s == t) {
            System.out.println("equals"); // right
        } else {
            System.out.println("not equals");
        }
    }

    @Test
    public void test5() {
        String s = "Hello";
        String t = s.toUpperCase();
        if (s == t) {
            System.out.println("equals");
        } else {
            System.out.println("not equals"); // right
        }
    }

    /**
     * 有时候会有个错误的印象是 ==比地址，equals比内容(String造成的错误印象) 因为java没有运算符重载，确实==是比较地址,
     * 但是equals不一定是比较内容, StringBuffer的equals方法没有重写，直接用的Object的 Object public
     * boolean equals(Object obj) { return (this == obj); }
     * 所以如果想比较内容，通常是重写equals
     */
    @Test
    public void test6() {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        String s = "abc";
        s1.append(s);
        s2.append(s);
        System.out.println("s1.equals(s2):\t\t" + (s1.equals(s2))); // false
        System.out.println("s1 == s2:\t\t" + (s1 == s2)); // false

        // s3是运行期才确定的。 @唐总
        // 是用stringbuilder实现的加，最后相当于new string。new 都是在堆里。
        // s是在编译期还是什么时期反正比运行期早的时间方到了常量池，每次都是一个对象。
        String s3 = s + "1";
        String s4 = s + "1";
        System.out.println("s3 == s4:\t\t" + (s3 == s4)); // false
        System.out.println("s3.equals(s4):\t\t" + (s3.equals(s4))); // true

        // 编译期的 @唐总
        String s5 = "abc" + "1";
        String s6 = "abc" + "1";
        System.out.println("s5 == s6:\t\t" + (s5 == s6)); // true
        System.out.println("s5.equals(s6):\t\t" + (s5.equals(s6))); // true

        Object obj1 = new Object();
        Object obj2 = new Object();
        System.out.println("obj1.equals(obj2):\t" + (obj1.equals(obj2)));// true
        System.out.println("obj1 == obj2:\t\t" + (obj1 == obj2));// false
    }

}
