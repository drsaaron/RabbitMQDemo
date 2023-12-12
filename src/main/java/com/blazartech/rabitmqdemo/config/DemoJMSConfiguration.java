/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo.config;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.MessageListener;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.Topic;
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
    
    @Value("${demo.topic.name}")
    private String topicName;

    @Value("${demo.queue.concurrency}")
    private String concurrency;

    @Autowired
    private ErrorHandler errorHandler;

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        container.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
      //  container.setDestination(destinationQueue());
        container.setDestinationName(queueName);
        container.setMessageListener(messageReceiver);
        container.setSessionTransacted(true);
        container.setConcurrency(concurrency);
        container.setErrorHandler(errorHandler);

        return container;
    }

    @Bean
    public Queue destinationQueue() {
        RMQDestination jmsDestination = new RMQDestination();
        jmsDestination.setDestinationName(queueName);
        jmsDestination.setQueue(true);
/*        jmsDestination.setAmqp(true);
        jmsDestination.setAmqpQueueName(queueName);*/
        log.info("destination queue: {}", jmsDestination);
        return jmsDestination;
    }
    
    @Bean
    public Topic publicationTopic() {
        RMQDestination jmsDestination = new RMQDestination(topicName, false, false);
        log.info("created destination {}", jmsDestination);
        return jmsDestination;
    }
}
