/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo;

import com.blazartech.rabitmqdemo.process.DemoItemProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class MessageReceiver implements MessageListener {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private DemoItemProcessor itemProcessor;

    /*@RabbitListener(queues = "${demo.queue.name}")
    public void onMessage(String json) throws JsonProcessingException {
        log.info("json to process = {}", json);

        DemoItem item = objectMapper.readValue(json, DemoItem.class);
        log.info("got item {}", item);

        itemProcessor.processItem(item);
    }*/

    @Override
    public void onMessage(Message message) {
        log.info("processing message {}", message);
        if (message instanceof TextMessage textMessage) {
            try {
                String json = textMessage.getText();
                log.info("json to process = {}", json);
            
                DemoItem item = objectMapper.readValue(json, DemoItem.class);
                log.info("got item {}", item);
                
                itemProcessor.processItem(item);
            } catch (JsonProcessingException|JMSException e) {
                throw new RuntimeException("error processing data: " + e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("message is not a text message");
        }
    }
}
