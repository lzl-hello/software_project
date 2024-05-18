package com.lzl.lab.service;

import com.lzl.lab.pojo.User;

public interface UserService {
    boolean register(User user);

    User login(User user);
}
