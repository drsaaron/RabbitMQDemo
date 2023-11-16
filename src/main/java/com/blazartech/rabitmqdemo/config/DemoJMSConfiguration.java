/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo.config;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.util.ErrorHandler;

/**
 *
 * @author aar1069
 */
@Configuration
@Slf4j
public class DemoJMSConfiguration {

    @Bean
    public ConnectionFactory jmsConnectionFactory() {
        RMQConnectionFactory connectionFactory = new RMQConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }
    
    @Autowired
    private MessageListener messageReceiver;
    
    @Value("${demo.queue.name}")
    private String queueName;
    
    @Value("${demo.queue.concurrency}")
    private String concurrency;
    
    @Autowired
    private ErrorHandler errorHandler;
    
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(queueName);
        container.setMessageListener(messageReceiver);
        container.setSessionTransacted(true);
        container.setConcurrency(concurrency);
        container.setErrorHandler(errorHandler);
        
        return container;
    }

}
