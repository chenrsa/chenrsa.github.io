package com.example.demo.annoation;

import com.example.demo.constant.SiglifeJobOption;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author chenrunzheng
 * @since 2020/10/13 16:15
 */
@Component
@Slf4j
public class TimeBoot implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext ac = event.getApplicationContext();
        Map<String, Object> beans =  ac.getBeansWithAnnotation(SiglifeJobClass.class);
        Object[] beanArr = beans.values().toArray();
        for (Object bean : beanArr) {
            if (bean instanceof AbstractSiglifeJob) {
                createQuartz((AbstractSiglifeJob) bean);
            }
        }

    }

    private void createQuartz(AbstractSiglifeJob task) {
        SiglifeJobOption option = task.getOption();

        JobBuilder jobDetailBuilder = JobBuilder
                .newJob(task.getClass())
                .withIdentity(option.getId(), option.getGroupId())
                .withDescription(option.getJobDescription())
                .storeDurably();

        if (option.getInitDataMap() != null) {
            jobDetailBuilder.usingJobData(new JobDataMap(option.getInitDataMap()));
        }
        JobDetail jobDetail = jobDetailBuilder.build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(option.getId())
                .withSchedule(option.getScheduleBuilder())
                .withDescription(option.getScheduleDescription())
                .build();

        try {
            if (option.getStartWithClear() != null && option.getStartWithClear()) {
                scheduler.deleteJob(JobKey.jobKey(option.getId(), option.getGroupId()));
            }
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("JobBoot", e);
        }
    }
}
