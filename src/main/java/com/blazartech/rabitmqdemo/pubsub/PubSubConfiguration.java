/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo.pubsub;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * see https://www.rabbitmq.com/tutorials/tutorial-three-spring-amqp.html
 *
 * @author aar1069
 */
@Configuration
public class PubSubConfiguration {
    
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("logs");
    }

  /*  @Bean
    public Queue autoDeleteQueue1() {
        return new Queue("spring-log-receiver-1");
    }

    @Bean
    public Queue autoDeleteQueue2() {
        return new Queue("spring-log-receiver-2");
    }*

    @Bean
    public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
    }*/

}
