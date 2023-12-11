/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class MessageSenderPublisherImpl implements MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
   /* @Autowired
    private TopicExchange topicExchange;*/
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public void sendMessage(DemoItem item) {
        log.info("publishing {}", item);
        try {
            rabbitTemplate.convertAndSend("logs", null, objectMapper.writeValueAsString(item));
        } catch(JsonProcessingException e) {
            throw new RuntimeException("error processing item: " + e.getMessage(), e);
        }
    }
    
}
