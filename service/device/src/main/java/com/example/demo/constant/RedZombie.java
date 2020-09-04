package com.example.demo.constant;

/**
 * @author chenrunzheng
 * @since 2020/9/3 9:39
 */
public class RedZombie extends Character {

    public RedZombie(IspeedInterface ispeedInterface, IattackInterface iattackInterface) {
        super(ispeedInterface, iattackInterface);
    }

    @Override
    void display() {
        System.out.println("colour is red");
    }


}
