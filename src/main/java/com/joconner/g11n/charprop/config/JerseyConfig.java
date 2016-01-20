package com.joconner.g11n.charprop.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by joconner on 1/8/16.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("com.joconner.g11n.charprop.resource");
    }
}
