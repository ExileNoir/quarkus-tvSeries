package com.infernalwhaler.microservices.proxy;

import com.infernalwhaler.microservices.model.TvSerie;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * @author Sdeseure
 * @project quarkus-web-interface
 * @date 14/10/2025
 */

@Path("/singlesearch/")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "serie-proxy")
public interface TvSeriesProxy {

    @GET
    @Path("/shows")
    TvSerie getTvSeries(@QueryParam("q") String title);
}
