package com.demo.flinkTest;

import com.demo.flinkTest.constant.DevOnlineDto;
import com.demo.flinkTest.constant.InfluxdbConfig;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenrunzheng
 * @since 2020/9/15 14:03
 */
public class Main3 {

    public static void main(String[] args) throws Exception {
   //     final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(InfluxdbConfig.url, InfluxdbConfig.token.toCharArray());
        Map<String,String> map = new HashMap<String, String>();
        DevOnlineDto devOnlineDto = DevOnlineDto.builder()
                .deviceid("SL7LL18BA099")
                .devicetype("7")
                .name("CHEN")
                .phone("15855556666")
                .online(1)
                .productid("SL4D41")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
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

       /* String query = String.format("from(bucket: \"%s\") |> range(start: -1h)", InfluxdbConfig.bucket);

        List<FluxTable> tables = influxDBClient.getQueryApi().query(query, InfluxdbConfig.org);
        System.out.println(tables);*/

    }
}
