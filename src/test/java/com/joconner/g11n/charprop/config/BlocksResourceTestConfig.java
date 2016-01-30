package com.joconner.g11n.charprop.config;

import com.joconner.g11n.charprop.resource.BlocksResource;
import com.joconner.g11n.charprop.service.BlocksService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author joconner
 */
@Configuration
public class BlocksResourceTestConfig {
    
    @Bean
    public BlocksService blocksService() {
        return new BlocksService();
    }
    
    @Bean
    public BlocksResource blocksResource() {
        return new BlocksResource();
    }
}
