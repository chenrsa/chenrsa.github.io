package com.example.demo.configuration;

import com.example.demo.constant.HelloResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author chenrunzheng
 * @since 2020/7/3 11:24
 */
@Component
@Configuration
public class ConfigBean {

    @Bean
    public HelloResponseDto HelloBean(){
        return HelloResponseDto.builder().name("chen").addresss("nanj").build();
    }
}
