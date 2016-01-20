/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joconner.g11n.charprop.resource;

import com.joconner.g11n.charprop.CharpropApplication;
import com.joconner.g11n.charprop.config.JerseyConfig;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author joconner
 */
public class HealthCheckResourceTest extends JerseyTest {
    
    private JerseyConfig config;
    
    public HealthCheckResourceTest() {
            
    }
    
    @Override
    public Application configure() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CharpropApplication.class);
        config = new JerseyConfig();
        config.property("contextConfig", context);  
        return config;
    }

    /**
     * Test of reportHealth method, of class HealthCheckResource.
     */
    @Test
    public void testReportHealth() {
        HealthCheckResource instance = new HealthCheckResource();
        HealthCheckStatus expResult = new HealthCheckStatus(Response.Status.OK.getStatusCode(), "OK");
        HealthCheckStatus result = instance.reportHealth();
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testHttpGetHealthCheck() {
        Response status = target("/health")
                .request(MediaType.APPLICATION_JSON)
                .get();
        HealthCheckStatus hcs = status.readEntity(HealthCheckStatus.class);
        assertEquals(Response.Status.OK.getStatusCode(), hcs.getStatus());
        assertEquals("OK", hcs.getMsg());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, status.getMediaType());
        assertEquals(Response.Status.OK.getStatusCode(), status.getStatus());
                
    }    
    
}
