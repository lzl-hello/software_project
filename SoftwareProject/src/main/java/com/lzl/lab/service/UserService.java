package com.lzl.lab.service;

import com.lzl.lab.pojo.User;

public interface UserService {
    boolean register(User user);

    User login(User user);

    User getUserById(Long id);

    void updateUser(Long userId,String username,String phonenumber,String newPassword);
}
