package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenrunzheng
 * @since 2020/4/23 13:58
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BleRssi {
    private Integer rssi;

    private String deviceid;
}
