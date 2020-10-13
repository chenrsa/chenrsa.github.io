package com.example.demo.annoation;

import com.example.demo.constant.SiglifeJobOption;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * @author chenrunzheng
 * @since 2020/10/13 16:24
 */
public abstract class AbstractSiglifeJob extends QuartzJobBean {

    protected abstract SiglifeJobOption getOption();
}
