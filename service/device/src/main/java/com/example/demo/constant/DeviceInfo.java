package com.example.demo.constant;

import com.example.demo.service.DevInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenrunzheng
 * @since 2020/9/27 11:25
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfo implements DevInfo {

    private String deviceId;

    private String mac;

    @Override
    public String getsn() {
        return deviceId;
    }
}
