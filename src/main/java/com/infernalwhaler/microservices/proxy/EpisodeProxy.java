package com.infernalwhaler.microservices.proxy;

import com.infernalwhaler.microservices.model.Episode;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

/**
 * @author Sdeseure
 * @project quarkus-web-interface
 * @date 14/10/2025
 */
@Path("/shows/")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "episode-proxy")
public interface EpisodeProxy {

    @GET
    @Path("{id}/episodes")
    List<Episode> get(@PathParam("id") Long id);


}
