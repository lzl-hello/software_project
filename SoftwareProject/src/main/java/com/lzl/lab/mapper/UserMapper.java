package com.lzl.lab.mapper;

import com.lzl.lab.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("insert into user (username, phonenumber,password) values (#{username}, #{phonenumber}, #{password})")
    boolean register(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(User user);

    @Select("SELECT * FROM user WHERE userId = #{id}")
    User getUserById(Long id);

    @Update("UPDATE user SET username = #{username}, phonenumber = #{phonenumber}, password = #{newPassword} WHERE userId = #{userId}")
    void updateUser(Long userId,String username,String phonenumber,String newPassword);
}
