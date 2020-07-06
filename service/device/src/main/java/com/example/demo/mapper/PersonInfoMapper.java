package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author chenrunzheng
 * @since 2020/7/3 14:17
 */

@Mapper
public interface PersonInfoMapper {

    User findUser(User name);
}
