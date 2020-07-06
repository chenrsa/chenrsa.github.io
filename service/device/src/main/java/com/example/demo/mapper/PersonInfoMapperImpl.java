package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenrunzheng
 * @since 2020/7/3 15:11
 */
@Service
public class PersonInfoMapperImpl implements PersonInfoMapper{

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;



    @Override
    public User findUser(User user) {
        return (User) sqlSessionTemplate.selectList("findUser", user);
    }
}
