package com.example.demo.constant;

/**
 * @author chenrunzheng
 * @since 2020/9/3 9:37
 */
public abstract class Character {

    private IspeedInterface ispeedInterface;
    private IattackInterface iattackInterface;

    public void speed(){
        ispeedInterface.Speed();
    }

    public void attack(){
        iattackInterface.attack();
    }

    Character(IspeedInterface ispeedInterface,IattackInterface iattackInterface){
        this.iattackInterface = iattackInterface;
        this.ispeedInterface = ispeedInterface;
    }
    abstract void display();

}
