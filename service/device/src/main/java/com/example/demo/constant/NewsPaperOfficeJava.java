package com.example.demo.constant;

import java.util.Observable;

/**
 * @author chenrunzheng
 * @since 2020/9/3 10:35
 */
public class NewsPaperOfficeJava extends Observable {

    private String message;
    private int time = 1;

    public void sendMessage(){
        message = "第"+time +"次发的新闻";
        setChanged();
        notifyObservers();
        time++;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
