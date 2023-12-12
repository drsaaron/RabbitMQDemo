/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class MessageSenderPublisherImpl implements MessageSender {

    @Autowired
    ConnectionFactory connectionFactory;
    
   @Autowired
    private Topic publicationTopic;
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public Message createMessage(Session session, DemoItem item) throws JMSException {
        try {
            String json = objectMapper.writeValueAsString(item);
            log.info("json = {}", json);
            TextMessage tm = session.createTextMessage(json);
            return tm;
        } catch(JsonProcessingException e) {
            throw new RuntimeException("error creating message: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void sendMessage(DemoItem item) {
        log.info("sending message {}", item);
        
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.send(publicationTopic, session -> createMessage(session, item));
    }
    
}
