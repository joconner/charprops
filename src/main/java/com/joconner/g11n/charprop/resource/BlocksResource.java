package com.joconner.g11n.charprop.resource;

import com.joconner.g11n.charprop.model.Block;
import com.joconner.g11n.charprop.service.BlocksService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author joconner
 */
@Component
@Path("/blocks")
@Produces(MediaType.APPLICATION_JSON)
public class BlocksResource {
    
    @Autowired
    BlocksService blockService;
    

    @GET
    public Response getBlocks() {
        List<Block> blocks = blockService.getBlocks();
        return Response.ok(blocks).build();
    }
//    
//    @GET
//    @Path("/block/{ch: \\p{Digit}+}")
//    public Response getBlockForDecimalDigits(@PathParam("ch") int ch) {
//        Block b = blockService.getBlockFor(ch);
//        return Response.ok(b).build();
//    }
//    
    @GET
    @Path("/block/{ch: \\p{XDigit}{1,6}}")
    public Response getBlockForHexDigits(@PathParam("ch") String strHex) {
        Response resp = null;
        try {
            int ch = Integer.parseUnsignedInt(strHex, 16);
            Block b = blockService.getBlockFor(ch);
            resp = Response.ok(b).build();
        } catch (NumberFormatException ex) {
            resp = Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        return resp;
    }
    
}
