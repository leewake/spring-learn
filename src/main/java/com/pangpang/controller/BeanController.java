package com.pangpang.controller;

import com.pangpang.service.HelloService;
import com.pangpang.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;

/**
 * Created by leewake on 2017/8/9 0009.
 */

@RestController
@RequestMapping("/bean")
public class BeanController {

    @RequestMapping("/getBeanByName")
            public String getBeanByName(){
                HelloService helloService = (HelloService) BeanUtil.getBean("helloService");
                try {
                    System.out.println(helloService.sayHello("bean mvn "));
                } catch (RemoteException e) {
                    e.printStackTrace();
        }
        return "demo";
    }

}
