package com.example.demo.constant;

import java.awt.Shape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenrunzheng
 * @since 2020/3/12 15:45
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryAppPicUrlResponse {
    private String picUrl;

    private String errmsg;


    private ErrorCode errcode;
}
