package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenrunzheng
 * @since 2020/3/24 15:10
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiRequest<T> {

    T data;
}
