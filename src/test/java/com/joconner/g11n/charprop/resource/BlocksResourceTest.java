package com.joconner.g11n.charprop.resource;

import com.joconner.g11n.charprop.config.BlocksResourceTestConfig;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author joconner
 */
public class BlocksResourceTest extends JerseyTest {
    
    public Application configure() {
          ApplicationContext context = new AnnotationConfigApplicationContext(BlocksResourceTestConfig.class);
          ResourceConfig config = new ResourceConfig();
          config.register(BlocksResource.class);
          config.property("contextConfig", context);
          return config;
    }
    
    
    @Test
    public void testBlocksListExists() {
        Response response = target("/blocks").request(MediaType.APPLICATION_JSON)
                .get();
        Assert.assertEquals(200, response.getStatus());
        List<Block> blocks = response.readEntity(List.class);
        Assert.assertNotNull(blocks);
        Assert.assertTrue(blocks.size() > 0);
    }
    
    @Test
    public void testReturnBlockFromDecimalCodePointUrl() {
        Response response = target("/blocks/block/125")
                .request(MediaType.APPLICATION_JSON)
                .get();
        Block b = response.readEntity(Block.class);
        Assert.assertEquals("Basic Latin", b.getName());
    }
    
    @Test
    @Ignore
    public void testReturnBlockFromHexCodePointUrl() {
        Response response = target("/blocks/block/0x0125")
                .request(MediaType.APPLICATION_JSON)
                .get();
        Block b = response.readEntity(Block.class);
        Assert.assertNotNull(b);
    }
}
