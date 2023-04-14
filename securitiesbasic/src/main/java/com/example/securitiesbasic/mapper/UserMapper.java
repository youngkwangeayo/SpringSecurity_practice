package com.example.securitiesbasic.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.securitiesbasic.entity.User;

@Mapper
public interface UserMapper {
    
    @Insert("insert into user (email,password,username) values (#{email},#{password},#{username})")
    int join(User user);

    
    @Select("select * from user where email=#{username;}")
    User findByUserName(String username);
}
