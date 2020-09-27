package com.example.demo.controller;

import com.example.demo.constant.HelloRequestDto;
import com.example.demo.constant.Singleton;
import com.example.demo.service.impl.Abshello;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenrunzheng
 * @since 2020/3/26 16:32
 */

@RestController
@RequestMapping("/springtest")
@Slf4j
public class TestController {

    @Autowired
    private Abshello abshello;

    @PostMapping("/hello")
    private String hello() {

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //Singleton singleton =new Singleton();//Singleton通过将构造方法限定为private避免了类在外部被实例化
        Singleton singleton1 = Singleton.getInstance();
        return abshello.dosome();
    }

    @PostMapping("/hi")
    @ApiOperation(value = "aaa",notes = "aaaaaaaddddddd")
    private String hi(@RequestBody HelloRequestDto requestDto) {
        log.info(requestDto.toString());
      //  TestPersonInfoEntity entity = abshello.computePay(requestDto.getName());
       /* User entity = abshello.bbb(User.builder().name(requestDto.getName()).build());
        abshello.hh(requestDto);
        return HelloResponseDto.builder()
                .addresss(entity.getAddr())
                .name(entity.getName())
                .build();*/
       return null;
    }

}
