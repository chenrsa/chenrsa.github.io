package com.example.demo.http;

import java.time.Duration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * @author chenrunzheng
 * @since 2020/3/12 15:00
 */
@Service
public class HttpclientImpl implements Httpclient {


    @Override
    public <T> Mono<T> async(String url, Object requestData, ParameterizedTypeReference<T> responseType) {

        WebClient webClient = WebClient.create();
        return webClient
                .post()
                .uri(url)
                .syncBody(requestData)
                .retrieve()
                .bodyToMono(responseType);
    }

    @Override
    public <T> T postasync(String url, Object requestData, ParameterizedTypeReference<T> responseType) {
        return async(url,requestData,responseType).block();
    }
    public <T> Mono<T> async(String uri, String method, Object request, ParameterizedTypeReference<T> responseType,
            Duration timeout) {

        WebClient webClient = WebClient.create();
        switch (method) {
            case "put":
                // 发送请求
                return webClient
                        .put()
                        .uri(uri)
                        .syncBody(request)
                        .retrieve()
                        .bodyToMono(responseType)
                        .timeout(timeout);
            case "get":
                // 发送请求
                return webClient
                        .get()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(responseType)
                        .timeout(timeout);
            case "delete":
                // 发送请求
                return webClient
                        .delete()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(responseType)
                        .timeout(timeout);
            default:
                // 发送请求
                return webClient
                        .post()
                        .uri(uri)
                        .syncBody(request)
                        .retrieve()
                        .bodyToMono(responseType)
                        .timeout(timeout);
        }

    }

}
