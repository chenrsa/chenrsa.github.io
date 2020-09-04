package com.example.demo.constant;

/**
 * @author chenrunzheng
 * @since 2020/9/3 9:49
 */
public class SlowSpeed implements IspeedInterface {

    @Override
    public void Speed() {
        System.out.println("slow speed 0.5");
    }
}
