package com.example.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenrunzheng
 * @since 2020/3/24 14:54
 */
@Configuration
@Data

@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private String host;

    private int port;

}
