package com.example.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author chenrunzheng
 * @since 2020/7/2 14:08
 */
@Configuration
@Component
@ConfigurationProperties(value = "spring.user")
@Data
public class UserInfoConfig {

    private String name;

    private String addr;
}
