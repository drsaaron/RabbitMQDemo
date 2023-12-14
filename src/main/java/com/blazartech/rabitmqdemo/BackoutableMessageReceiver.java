/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo;

import com.blazartech.rabitmqdemo.process.DemoItemProcessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import static org.springframework.amqp.support.AmqpHeaders.DELIVERY_TAG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * a message handler using native RabbitListener rather than JMS that will
 * catch any exception and reject the message, putting it to the backout queue.
 * @author aar1069
 */
@Component
@Slf4j
public class BackoutableMessageReceiver {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private DemoItemProcessor itemProcessor;
    
    @RabbitListener(queues = "${demo.backoutable.queue.name}")
    public void onMessage(Message message, Channel channel, @Header(DELIVERY_TAG) long deliveryTag) throws JsonProcessingException, IOException {
        log.info("got message {}", message);

        try {
            DemoItem item = new DemoItem();
            itemProcessor.processItem(item);
        } catch (Exception e) {
            log.error("caught exception: " + e.getMessage(), e);
            channel.basicReject(deliveryTag, false);
        }
    }
}
