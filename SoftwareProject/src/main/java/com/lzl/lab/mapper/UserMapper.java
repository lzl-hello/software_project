package com.lzl.lab.mapper;

import com.lzl.lab.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (username, phonenumber,password) values (#{username}, #{phonenumber}, #{password})")
    boolean register(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(User user);
}
