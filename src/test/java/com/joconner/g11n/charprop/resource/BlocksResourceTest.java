package com.joconner.g11n.charprop.resource;

import com.joconner.g11n.charprop.CharpropApplication;
import com.joconner.g11n.charprop.config.BlocksResourceTestConfig;
import com.joconner.g11n.charprop.config.JerseyConfig;
import com.joconner.g11n.charprop.model.Block;
import java.util.List;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author joconner
 */
public class BlocksResourceTest extends JerseyTest {

    @Autowired
    ResourceConfig config;
    
    public Application configure() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BlocksResourceTestConfig.class);
        ResourceConfig config = new ResourceConfig();
        config.register(BlocksResource.class);
        config.property("contextConfig", context);  
        return config;
    }
    
    
    @Test
    public void testBlocksListExists() {
        Response response = target("/blocks")
                .request(MediaType.APPLICATION_JSON)
                .get();
        Assert.assertEquals(200, response.getStatus());
        List<Block> blocks = response.readEntity(List.class);
        Assert.assertNotNull(blocks);
        Assert.assertTrue(blocks.size() > 0);
    }
    
    @Test
    public void testReturnBlockFromHexCodePointUrl() {
        Response response = target("/blocks/block/004A")
                .request(MediaType.APPLICATION_JSON)
                .get();
        Block b = response.readEntity(Block.class);
        Assert.assertNotNull(b);
        Assert.assertEquals("Basic Latin", b.getName());
    }
    
}
