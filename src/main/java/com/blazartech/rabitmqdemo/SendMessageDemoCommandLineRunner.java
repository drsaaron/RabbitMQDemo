/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class SendMessageDemoCommandLineRunner implements CommandLineRunner {

    @Autowired
    private MessageSender messageSender;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("sending message");
        
        DemoItem[] items = {
            new DemoItem(1, "Eddie", 60),
            new DemoItem(2, "Frank", 65),
            new DemoItem(3, "Eric", 80),
            new DemoItem(4, "Jimmy", 81),
            new DemoItem(5, "Jeff", -1)
        };
        
        Arrays.asList(items).stream()
                .peek(i -> log.info("sending message {}", i))
                .forEach(i -> messageSender.sendMessage(i));
    }
    
}
