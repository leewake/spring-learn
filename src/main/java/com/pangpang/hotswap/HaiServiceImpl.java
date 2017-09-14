package com.pangpang.hotswap;

import com.pangpang.service.HelloService;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

/**
 * 与之前的HelloService并列，形成两个服务，以便切换
 * Created by leewake on 2017/9/13 0013.
 */

@Service
public class HaiServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) throws RemoteException {
        return String.format("Hai %s", name);
    }
}
