package com.demo.flinkTest;

import com.demo.flinkTest.constant.DevOnlineDto;
import com.demo.flinkTest.sink.InfluxDBSink;
import com.demo.flinkTest.source.mySourceFunction;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author chenrunzheng
 * @since 2020/9/15 14:03
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

     /*   //下面这些写死的参数可以放在配置文件中，然后通过 parameterTool 获取
        final RMQConnectionConfig connectionConfig = new RMQConnectionConfig
                .Builder().setHost(RabbitmqConfig.host).setVirtualHost("/")
                .setPort(RabbitmqConfig.port).setUserName(RabbitmqConfig.usrname).setPassword(RabbitmqConfig.password)
                .build();
        ObjectMapper mapper = new ObjectMapper();
*/

     /*   DataStreamSource<DevOnlineDto> dataStreamSource = (DataStreamSource<DevOnlineDto>) env.addSource(new RMQSource<>(connectionConfig,
                "flink-test",
                true,
                new SimpleStringSchema()))
                .setParallelism(1)
                .map(new MapFunction<String, DevOnlineDto>() {
                    public DevOnlineDto map(String s) throws Exception {
                        return DevOnlineDto.builder()
                                .deviceid("SL7LL18BA099")
                                .devicetype("7")
                                .name("CHEN")
                                .phone("15855556666")
                                .online(1)
                                .productid("SL4D41")
                                .build();
                    }
                });*/

        List<Integer> datasource=new ArrayList<Integer>();

        datasource.add(10);

        DataStreamSource<Long> source = env.addSource(new mySourceFunction());

        DataStream<DevOnlineDto> res = source.map(new MapFunction<Long, DevOnlineDto>() {
            public DevOnlineDto map(Long input) throws Exception {
                return DevOnlineDto.builder()
                        .deviceid("SL7LL18BA099")
                        .devicetype("7")
                        .name("CHEN")
                        .phone("15855556666")
                        .online(1)
                        .productid("SL4D41")
                        .timestamp(new Timestamp(System.currentTimeMillis()))
                        .build();
            }
        });
        res.print();

        res.addSink(new InfluxDBSink());

        //如果想保证 exactly-once 或 at-least-once 需要把 checkpoint 开启
       //env.enableCheckpointing(10000);
        env.execute("flink learning connectors influxdb");
    }
}
