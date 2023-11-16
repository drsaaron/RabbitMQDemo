/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.blazartech.rabitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 *
 * @author aar1069
 */
@SpringBootApplication
@EnableJms
public class RabbitMQDemo {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQDemo.class, args);
    }
}
