package com.example.demo.constant;

import java.util.Observable;
import java.util.Observer;

/**
 * @author chenrunzheng
 * @since 2020/9/3 10:36
 */
public class FirstObserverJava implements Observer {

    private String message;
    Observable mObservable;

    public String getMessage() {
        return message;
    }
    @Override
    public void update(Observable o, Object arg) {
        mObservable = o;
        if(mObservable instanceof NewsPaperOfficeJava){
            message = "FirstObserver 收到了" + ((NewsPaperOfficeJava) mObservable).getMessage();
        }
    }
}
