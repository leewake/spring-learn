package com.pangpang.controller;

import com.pangpang.service.ActiveMqProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * Created by leewake on 2017/8/8 0008.
 */

@RestController
@RequestMapping("/activeMq")
public class ActiveMqController {

    @Autowired
    private ActiveMqProducer producer;

    @RequestMapping("/produce")
    public void produceActiveMq(){

        Destination destination = new ActiveMQQueue("produce.queue");

        for (int i = 0; i < 100; i++) {
            producer.sendMessage(destination, "第" + i + "个消息到来");
        }
    }
}
