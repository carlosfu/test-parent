package com.carlosfu.annotation.custom;

/**
 * 
 * @author leifu(来自圣思园)
 * @Time 2014年10月15日
 */
@AnnotationTest(value2 = EnumTest.Welcome)
public class AnnotationUsage {
    @AnnotationTest(value1 = {"world", "ABCD"}, value2 = EnumTest.World)
    public void method() {
        System.out.println("usage of annotation");
    }

    public static void main(String[] args) {
        AnnotationUsage usage = new AnnotationUsage();

        usage.method();
    }
}
