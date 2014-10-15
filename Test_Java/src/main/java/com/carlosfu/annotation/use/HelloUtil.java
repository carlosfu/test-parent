package com.carlosfu.annotation.use;

import com.carlosfu.annotation.annotation.HelloWorld;
import com.carlosfu.annotation.annotation.Yts;

@Yts
public class HelloUtil {

    @HelloWorld(name = " 小明 ")
    @Yts
    public void sayHello(String name) {
        if (name == null || name.equals("")) {
            System.out.println("hello world!");
        } else {
            System.out.println(name + "say hello world!");
        }
    }
    
    public static void main(String[] args) {
        new HelloUtil().sayHello("a");
    }
}