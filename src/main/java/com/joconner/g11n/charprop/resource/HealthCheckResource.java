package com.joconner.g11n.charprop.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

/**
 * Created by joconner on 1/8/16.
 */
@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class HealthCheckResource {

    @GET
    public HealthCheckStatus reportHealth() {
        return new HealthCheckStatus(Response.Status.OK.getStatusCode(), "OK");
    }

}
