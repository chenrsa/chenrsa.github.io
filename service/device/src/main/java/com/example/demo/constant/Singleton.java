package com.example.demo.constant;

/**
 * @author chenrunzheng
 * @since 2020/5/15 10:06
 */
public class Singleton {

    private Singleton() {}
    private static Singleton single=null;
    //静态工厂方法
    public static Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }
}
