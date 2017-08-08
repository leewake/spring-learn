package com.pangpang.service.impl;

import com.pangpang.service.ActiveMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * Created by leewake on 2017/8/8 0008.
 */

@Service("producer")
public class ActiveMqProducerImpl implements ActiveMqProducer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Override
    public void sendMessage(Destination destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    @JmsListener(destination = "consume.queue")
    public void consumeMessage(String text){
        System.out.println("从consume.queue队列收到的回复报文为:"+text);
    }
}
