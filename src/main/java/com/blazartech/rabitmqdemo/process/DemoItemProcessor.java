/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blazartech.rabitmqdemo.process;

import com.blazartech.rabitmqdemo.DemoItem;

/**
 *
 * @author scott
 */
public interface DemoItemProcessor {
    
    void processItem(DemoItem item, boolean throwException);
}
