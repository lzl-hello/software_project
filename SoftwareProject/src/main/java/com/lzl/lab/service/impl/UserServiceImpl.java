package com.lzl.lab.service.impl;

import com.lzl.lab.mapper.UserMapper;
import com.lzl.lab.pojo.User;
import com.lzl.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean register(User user){
        return userMapper.register(user);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(Long userId,String username,String phonenumber,String newPassword) {
        userMapper.updateUser(userId,username,phonenumber,newPassword);
    }
}
