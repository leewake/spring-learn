package com.pangpang.controller;

import com.pangpang.Domain.User;
import com.pangpang.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leewake on 2017/8/4 0004.
 */

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/putUser")
    public User putUser(){
            User user = redisService.getUser();
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/getUser")
    public User getUser(){
        User user = redisService.getUser();
        System.out.println(user.toString());
        return user;
    }

}
