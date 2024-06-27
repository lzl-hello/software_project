package com.lzl.lab.controller;

import com.lzl.lab.pojo.Result;
import com.lzl.lab.pojo.User;
import com.lzl.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;
import java.util.Objects;


@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        System.out.println("用户注册");

        String originalPassword = user.getPassword();
        String hashPassword = hashPassword(originalPassword);
        user.setPassword(hashPassword);

        System.out.println(user);

        if (userService.register(user)) {
            return Result.success("注册成功");
        } else {
            return Result.error("注册失败");
        }

    }

    // 将哈希处理的方法单独写一个方法
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 将字节数组转换为16进制字符串的方法
    private String bytesToHex(byte[] hash) {
        return HexFormat.of().formatHex(hash);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        System.out.println("用户登录：");

        String inputPassword = user.getPassword();
        String hashedInputPassword = hashPassword(inputPassword);
        user.setPassword(hashedInputPassword);

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

    @GetMapping ("/getUserById")
    public Result getUserById(@RequestParam Long userId) {
        System.out.println("获取用户信息：");
        System.out.println(userId);
        User u = userService.getUserById(userId);
        if (u != null) {
            return Result.success("获取用户信息成功", u);
        } else {
            return Result.error("获取用户信息失败");
        }
    }

    @PutMapping ("/updateUser")
    public Result updateUser(@RequestParam Long userId, @RequestParam String username,@RequestParam String phonenumber, @RequestParam String oldPassword,@RequestParam String newPassword){
        System.out.println("更新用户信息：");

        User u = userService.getUserById(userId);
        System.out.println(u.getPassword());
        String hashOldPassword = hashPassword(oldPassword);
        System.out.println(hashOldPassword);

        if (!Objects.equals(u.getPassword(), hashOldPassword)) {
            System.out.println("原密码错误");
            return Result.error("原密码错误");
        } else {
            System.out.println("原密码正确");
            String hashPassword = hashPassword(newPassword);
            userService.updateUser(userId,username,phonenumber,hashPassword);
            return Result.success("更新用户信息成功");
        }

//        String hashPassword = hashPassword(newPassword);
//
//        userService.updateUser(userId,username,phonenumber,hashPassword);
//        return Result.success("更新用户信息成功");
    }
}
