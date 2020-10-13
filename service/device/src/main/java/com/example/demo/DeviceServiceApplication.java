package com.example.demo;


import com.example.demo.annoation.SiglifeApplication;
import com.example.demo.annoation.EnableTimer;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author chenrunzheng
 * @since 2020/3/12 16:32
 */

@SiglifeApplication
@EnableScheduling
@EnableTimer
public class DeviceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeviceServiceApplication.class, args);
    }

}
