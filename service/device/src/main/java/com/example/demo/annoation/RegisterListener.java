package com.example.demo.annoation;

import com.example.demo.constant.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author chenrunzheng
 * @since 2020/5/25 11:40
 */
@Component
public class RegisterListener implements ApplicationListener<UserRegisterEvent> {

    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        System.out.println("事件接收到了：11111111");
    }
}
