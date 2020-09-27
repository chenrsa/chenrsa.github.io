package com.example.demo.constant;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenrunzheng
 * @since 2020/3/24 15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelloRequestDto {

    @NotNull
    private String name;

    @NotNull(message = "age 值错误")
    private String age;

    private AlarmType1 alarmType;
}
