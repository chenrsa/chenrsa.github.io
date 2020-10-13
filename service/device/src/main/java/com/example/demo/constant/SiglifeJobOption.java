package com.example.demo.constant;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;

/**
 * @author chenrunzheng
 * @since 2020/10/13 16:45
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class SiglifeJobOption {
    private String id;
    private String groupId;
    private String jobDescription;
    private Map<String, Object> initDataMap;
    private ScheduleBuilder<? extends Trigger> scheduleBuilder;
    private String scheduleDescription;

    private Boolean startWithClear;
}
