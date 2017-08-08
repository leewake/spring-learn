package com.pangpang.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by leewake on 2017/8/8 0008.
 */

@Component
public class ActiveMqSecondConsumer {

    @JmsListener(destination = "produce.queue")
    public void receiveQueue(String text){
        System.out.println("SecondConsumer收到的报文为：" + text);
    }

}
