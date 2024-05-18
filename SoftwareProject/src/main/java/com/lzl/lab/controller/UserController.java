package com.lzl.lab.controller;

import com.lzl.lab.pojo.Result;
import com.lzl.lab.pojo.User;
import com.lzl.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        System.out.println("用户注册");
        System.out.println(user);

        if (userService.register(user)) {
            return Result.success("注册成功");
        } else {
            return Result.error("注册失败");
        }

    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        System.out.println("用户登录：");
        System.out.println(user);

//        User u = userService.login(user);
//        return u != null ? Result.success() : Result.error("登录失败");
        User u = userService.login(user);
        System.out.println(u);
        if (u != null) {
            return Result.success("登录成功", u);
        } else {
            return Result.error("登录失败");
        }
    }
}
