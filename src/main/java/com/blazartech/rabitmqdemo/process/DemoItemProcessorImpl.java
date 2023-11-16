/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.rabitmqdemo.process;

import com.blazartech.rabitmqdemo.DemoItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
@Slf4j
public class DemoItemProcessorImpl implements DemoItemProcessor {

    @Value("${demo.throwException}")
    private boolean throwException;
    
    @Override
    public void processItem(DemoItem item) {
        log.info("here we go with item {}", item);
        
        if (throwException && item.getId() % 4 == 0) {
            log.info("throwing an intentional exception");
            throw new RuntimeException("intentional for item " + item.getId());
        }
    }
    
}
