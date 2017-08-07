package com.pangpang.controller.rmi;

import com.pangpang.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by leewake on 2017/8/7 0007.
 */
@RestController
public class RmiClientController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/rmi/consume")
    public void consume() throws RemoteException, NotBoundException, MalformedURLException {

        String url = "rmi://localhost:1099/com.pangpang.service.HelloServiceImpl";

        helloService = (HelloService) Naming.lookup(url);

        System.out.println(helloService.sayHello("pangpang"));
    }
}
