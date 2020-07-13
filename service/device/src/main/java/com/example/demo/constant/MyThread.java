package com.example.demo.constant;

/**
 * @author chenrunzheng
 * @since 2020/7/8 9:55
 */
public class MyThread  implements  Runnable{

    private int count = 5;

    @Override
    public void run() {
        count--;
        System.out.println("线程"+ Thread.currentThread().getName() +count);
    }
}
