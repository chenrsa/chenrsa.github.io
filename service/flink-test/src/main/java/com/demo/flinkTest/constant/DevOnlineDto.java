package com.demo.flinkTest.constant;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenrunzheng
 * @since 2020/9/15 14:42
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DevOnlineDto {



    private String deviceid;

    private String devicetype;

    private String productid;

    private String name;

    private String phone;

    private Integer online;

    private Timestamp timestamp;
}
