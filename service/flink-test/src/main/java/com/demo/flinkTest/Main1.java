package com.demo.flinkTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.flink.api.common.functions.FoldFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author chenrunzheng
 * @since 2020/9/15 14:03
 */
public class Main1 {


    private static final String[] TYPE = { "苹果", "梨", "西瓜", "葡萄", "火龙果" };

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
/*
        DataStreamSource<String> dataStreamSource = env.readTextFile("D:\\Temp\\demo\\service\\flink-test\\src\\main\\java\\com\\demo\\flinkTest\\constant\\hello.txt");
        dataStreamSource.map((MapFunction<String, String>) s -> s + "dd");
        dataStreamSource.print();

        env.execute("heeee");*/

        /*final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String[] words = {"apple","orange","banana","watermelon"};
        //创建DataSource
        //根据给定的元素创建一个数据流，元素必须是相同的类型，比如全部为String，或者全部为int。
        DataStreamSource<String> ds = env.fromElements(words);
        //Transformations
        //对DataStream应用一个flatMap转换。对DataStream中的每一个元素都会调用FlatMapFunction接口的具体实现类。flatMap方法可以返回任意个元素，当然也可以什么都不返回。
        SingleOutputStreamOperator<String> flatMap = ds.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                out.collect(value);
                out.collect(value.toUpperCase());
            }
        });
        //sinks打印出信息
        //给DataStream添加一个Sinks
        flatMap.addSink(new SinkFunction<String>() {
            @Override
            public void invoke(String value) throws Exception {
                System.out.println(value);
            }
        });

        env.execute("Flink Streaming Java API Skeleton");*/

        DataStreamSource<Tuple2<String, Integer>> orderSource = env.addSource(new SourceFunction<Tuple2<String, Integer>>() {
            private volatile boolean isRunning = true;
            private final Random random = new Random();
            @Override
            public void run(SourceContext<Tuple2<String, Integer>> ctx) throws Exception {
                while (isRunning) {
                    TimeUnit.SECONDS.sleep(1);
                    ctx.collect(Tuple2.of(TYPE[random.nextInt(TYPE.length)], 1));
                }
            }

            @Override
            public void cancel() {
                isRunning = false;
            }

        }, "order-info");


//        orderSource.keyBy(0)
//                //将上一元素与当前元素相加后，返回给下一元素处理
//                .reduce(new ReduceFunction<Tuple2<String,Integer>>() {
//                    @Override
//                    public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2)
//                            throws Exception {
//                        return Tuple2.of(value1.f0, value1.f1+value2.f1);
//                    }
//                })
//                .print();
        /*orderSource.keyBy(new KeySelector<Tuple2<String,Integer>, String>(){
            @Override
            public String getKey(Tuple2<String, Integer> value) throws Exception {
                return "";
            }

        })*/
        orderSource.timeWindowAll(Time.seconds(5))
                //这里用HashMap做暂存器
                .fold(new HashMap<String, Integer>(), new FoldFunction<Tuple2<String,Integer>, Map<String, Integer>>() {
                    @Override
                    public Map fold(Map<String, Integer> accumulator, Tuple2<String, Integer> value) throws Exception {
                        accumulator.put(value.f0, (Integer)accumulator.getOrDefault(value.f0, 0)+value.f1);
                        return accumulator;
                    }
                })
                .print();

        env.execute("Flink Streaming Java API Skeleton");


    }
}
