package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenrunzheng
 * @since 2020/3/12 15:40
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetUrlRequest {
    private String screenWidth;

    private String screenHeight;

    private String picName;
}
