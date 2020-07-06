package com.example.demo.service.impl;

import com.example.demo.constant.HelloRequestDto;
import com.example.demo.constant.UserRegisterEvent;
import com.example.demo.entity.TestPersonInfoEntity;
import com.example.demo.entity.User;
import com.example.demo.mapper.PersonInfoMapper;
import com.example.demo.repository.TestPersonInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author chenrunzheng
 * @since 2020/3/12 14:01
 */
@Service
public class Abshello extends TestImpl<HelloRequestDto> {

    private final TestPersonInfoRepository personInfoRepository;


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PersonInfoMapper personInfoMapper;


    Abshello(TestPersonInfoRepository personInfoRepository){
        super("AA");
        this.personInfoRepository = personInfoRepository;

    }


    @Override
    public TestPersonInfoEntity computePay(String name) {
        applicationContext.publishEvent(new UserRegisterEvent("a"));
        return  personInfoRepository.findFirstByName(name);
    }

    @Override
    public User bbb(User user) {
        applicationContext.publishEvent(new UserRegisterEvent("a"));
        return personInfoMapper.findUser(user);

    }

    @Override
    protected String aaa(HelloRequestDto data) {
        return "fasdf";
    }
}
