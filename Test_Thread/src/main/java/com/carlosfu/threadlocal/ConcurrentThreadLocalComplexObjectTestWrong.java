package com.carlosfu.threadlocal;

import java.util.Random;

/**
 * 对象类型的ThreadLocal（错误）
 * @author leifu
 * @Date 2015年1月13日
 * @Time 上午10:13:23
 */
public class ConcurrentThreadLocalComplexObjectTestWrong {

    private static ThreadLocal<Student> threadData = new ThreadLocal<Student>();

    private static Student student = new Student();
    
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt(100);
                    student.setName("a");
                    student.setAge(data);
                    threadData.set(student);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            Student student = threadData.get();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get student data: " + student);
        }
    }

    static class B {
        public void get() {
            Student student = threadData.get();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get student data: " + student);
        }
    }

}
