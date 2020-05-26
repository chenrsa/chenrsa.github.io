package com.example.demo.service.impl;

import com.example.demo.entity.TestPersonInfoEntity;

/**
 * @author chenrunzheng
 * @since 2020/3/12 11:33
 */
public class AbsTest extends TestImpl {


    AbsTest(String AA) {
        super(AA);
    }

    @Override
    public TestPersonInfoEntity computePay(String name) {
        return null;
    }

    @Override
    protected String aaa(Object data) {
        return "fvvv";
    }

    public String dosome() {
        return "cccccccc";
    }
}
