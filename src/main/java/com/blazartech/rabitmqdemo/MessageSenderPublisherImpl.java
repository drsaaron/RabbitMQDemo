/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo;

import jakarta.jms.Destination;
import jakarta.jms.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class MessageSenderPublisherImpl extends MessageSenderBaseImpl {

    @Autowired
    private Topic publicationTopic;
    
    @Override
    public Destination getDestination() {
        return publicationTopic;
    }

    @Override
    public String getMethodName() {
        return "publication";
    }

    
    
}
