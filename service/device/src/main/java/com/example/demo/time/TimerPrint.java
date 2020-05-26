package com.example.demo.time;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author chenrunzheng
 * @since 2020/5/25 15:22
 */
@Component
@Slf4j
public class TimerPrint {

    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        log.info("this is scheduler task runing  "+ new Date(System.currentTimeMillis()));
    }
}
