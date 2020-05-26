package com.example.demo.constant;


import org.springframework.context.ApplicationEvent;

/**
 * @author chenrunzheng
 * @since 2020/5/25 12:13
 */
public class UserRegisterEvent extends ApplicationEvent {

    public UserRegisterEvent(Object source) {
        super(source);
    }
}
