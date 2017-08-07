package com.pangpang.controller.rmi;

import com.pangpang.service.HelloService;
import com.pangpang.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by leewake on 2017/8/7 0007.
 */

@RestController
public class RmiServeController {

    @RequestMapping("/rmi/depoly")
    public void depoly() throws RemoteException, MalformedURLException {
        int port = 1099;
        String url = "rmi://localhost:1099/com.pangpang.service.HelloServiceImpl";
        LocateRegistry.createRegistry(port);
        Naming.rebind(url, new HelloServiceImpl());
    }

}
