package com.demo.flinkTest;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * @author chenrunzheng
 * @since 2020/9/15 14:03
 */
public class Main1 {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> dataStreamSource = env.readTextFile("D:\\Temp\\demo\\service\\flink-test\\src\\main\\java\\com\\demo\\flinkTest\\constant\\hello.txt");
        dataStreamSource.map((MapFunction<String, String>) s -> s + "dd");
        dataStreamSource.print();

        env.execute("heeee");


        SingleOutputStreamOperator<String> streamOperator = env.addSource(new SourceFunction<String>() {
            @Override
            public void run(SourceContext<String> sourceContext) throws Exception {

            }

            @Override
            public void cancel() {

            }
        });
    }
}
