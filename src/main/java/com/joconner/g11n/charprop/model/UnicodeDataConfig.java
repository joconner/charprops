/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joconner.g11n.charprop.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author joconner
 */
@Component
@ConfigurationProperties(prefix = "unicode")
public class UnicodeDataConfig {
    private String blocks;

    public UnicodeDataConfig() {
    }

    
    
    public String getBlocks() {
        return blocks;
    }

    public void setBlocks(String blocks) {
        this.blocks = blocks;
    }
    
    
}
