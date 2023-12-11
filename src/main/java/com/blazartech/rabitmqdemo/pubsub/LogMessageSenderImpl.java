/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class LogMessageSenderImpl implements LogMessageSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;   // configured in Tut3Config above

    @Override
    public void sendMessage(String logMessage) {
        log.info("publishing message");
        template.convertAndSend(fanout.getName(), "", logMessage);
    }
}
