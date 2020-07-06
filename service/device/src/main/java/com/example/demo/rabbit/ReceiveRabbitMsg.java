package com.example.demo.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenrunzheng
 * @since 2020/6/3 10:15
 */
@Slf4j
@Component
public class ReceiveRabbitMsg {

    @RabbitHandler
    @RabbitListener(queues = "api.core")
    public void receive(String msg) {
        System.out.println("dddddddddddddddddddddddddddddd");
        System.out.println(msg);
        log.info("api.user receive message: "+msg);
    }
}
