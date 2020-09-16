package com.demo.flinkTest.source;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * @author chenrunzheng
 * @since 2020/9/16 10:18
 */
public class mySourceFunction implements SourceFunction<Long> {

    private boolean ISRUNNING = true;

    private Long COUNT = 0L;

    @Override
    public void run(SourceContext<Long> sourceContext) throws Exception {
        while (ISRUNNING) {
            sourceContext.collect(COUNT);
            COUNT++;
            Thread.sleep(2000);
        }
    }

    @Override
    public void cancel() {
        ISRUNNING = false;
    }
}
