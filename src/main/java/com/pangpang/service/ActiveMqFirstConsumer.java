package com.pangpang.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by leewake on 2017/8/8 0008.
 */

@Component
public class ActiveMqFirstConsumer {

    @JmsListener(destination = "produce.queue")
    @SendTo("consume.queue")
    public String receiveQueue(String text){
        System.out.println("FirstConsumer收到的报文为：" + text);
        return "从FirstConsumer发出" + text;
    }

}
