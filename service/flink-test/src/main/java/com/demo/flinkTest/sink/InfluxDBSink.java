package com.demo.flinkTest.sink;

import com.demo.flinkTest.constant.DevOnlineDto;
import com.demo.flinkTest.constant.InfluxdbConfig;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;


/**
 * @author chenrunzheng
 * @since 2020/9/15 14:41
 */
public class InfluxDBSink extends RichSinkFunction<DevOnlineDto> {


    private transient InfluxDBClient influxDBClient;

    public InfluxDBSink() {

    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        influxDBClient = InfluxDBClientFactory.create(InfluxdbConfig.url, InfluxdbConfig.token.toCharArray());

    }

    @Override
    public void invoke(DevOnlineDto devOnlineDto, Context context) throws Exception {

        Map<String,String> map = new HashMap<String, String>();
        map.put("deviceid",devOnlineDto.getDeviceid());
        map.put("devicetype",devOnlineDto.getDevicetype());
        map.put("productid",devOnlineDto.getProductid());
        map.put("phone",devOnlineDto.getPhone());
        map.put("name",devOnlineDto.getName());

        Point point = Point
                .measurement("online")
                .time(Instant.now(), WritePrecision.NS)
                .addTags(map)
                .addField("online",1);

        try (WriteApi writeApi = influxDBClient.getWriteApi()) {
            writeApi.writePoint(InfluxdbConfig.bucket,InfluxdbConfig.org,point);
        }
    }

   /* @Override
    public void close() {

        influxDBClient.close();
    }*/
}
