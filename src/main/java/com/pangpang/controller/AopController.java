package com.pangpang.controller;

import com.pangpang.aop.LoggerManage;
import com.pangpang.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leewake on 2017/8/1 0001.
 */

@RestController
@RequestMapping("/aop")
public class AopController {

    private String test = "test";

    @RequestMapping("/print")
    @LoggerManage(description = "<---注解起作用--->")
    public void printAop(String string){
        string = test;
        System.out.println("Now, start to test aop" + string);
    }

    @Autowired
    private MailService mailService;

    @RequestMapping("/send")
    public void simpleMail(String tmp) {
        System.out.println(tmp);
        mailService.sendSimpleMail("leewake@163.com",
                "a simple mail", "Hello, this is a simple mail");
    }
}
