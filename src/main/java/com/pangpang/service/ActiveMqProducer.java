package com.pangpang.service;


import javax.jms.Destination;

/**
 * Created by leewake on 2017/8/8 0008.
 */
public interface ActiveMqProducer {

    void sendMessage(Destination destination, final String message);

}
