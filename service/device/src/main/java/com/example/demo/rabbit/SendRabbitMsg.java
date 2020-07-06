package com.example.demo.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenrunzheng
 * @since 2020/6/3 10:13
 */
@Component
public class SendRabbitMsg {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg){
        rabbitTemplate.convertAndSend("coreExchange","api.core.cc",msg);


    }
}
