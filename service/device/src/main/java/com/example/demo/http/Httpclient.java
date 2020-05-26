package com.example.demo.http;

import java.time.Duration;
import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Mono;

/**
 * @author chenrunzheng
 * @since 2020/3/12 14:55
 */


public interface Httpclient {

    <T> Mono<T> async(String url,Object requestData, ParameterizedTypeReference<T> responseType);

    <T> T postasync(String url,Object requestData, ParameterizedTypeReference<T> responseType);

    <T> Mono<T> async(String uri, String method, Object request, ParameterizedTypeReference<T> responseType,
            Duration timeout);
}
