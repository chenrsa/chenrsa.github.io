package com.example.demo.service.impl;

import com.example.demo.entity.TestPersonInfoEntity;
import com.example.demo.entity.User;

/**
 * @author chenrunzheng
 * @since 2020/3/12 11:13
 */
abstract class TestImpl<Q>  {

     String s;

    TestImpl(String AA){
        s = AA;
    }

    public String dosome() {
        return "AAAAAAAAAA";
    }



    public void hh(Q data){
        aaa(data);

    }

    public abstract TestPersonInfoEntity computePay(String name);

    public abstract User bbb(User user);
    protected abstract String aaa(Q data);

}
