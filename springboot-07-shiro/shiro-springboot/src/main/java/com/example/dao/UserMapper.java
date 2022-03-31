package com.example.dao;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User queryUserByName(String name);
}
