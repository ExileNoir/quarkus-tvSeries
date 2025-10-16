package com.infernalwhaler.microservices;

import com.infernalwhaler.microservices.model.Episode;
import com.infernalwhaler.microservices.model.TvSerie;
import com.infernalwhaler.microservices.proxy.EpisodeProxy;
import com.infernalwhaler.microservices.proxy.TvSeriesProxy;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sdeseure
 * @project quarkus-web-interface
 * @date 14/10/2025
 */

@Path("/api/tvseries")
public class TvSeriesResource {

    @RestClient
    TvSeriesProxy proxy;

    @RestClient
    EpisodeProxy episodeProxy;

    private List<TvSerie> tvSerieList = new ArrayList<>();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTvSeries(@QueryParam("title") final String title) {
        final TvSerie tvSerie = proxy.getTvSeries(title);
        final List<Episode> episodes = episodeProxy.get(tvSerie.getId());

        tvSerie.setEpisodes(episodes);
        tvSerieList.add(tvSerie);
        return Response.status(Response.Status.OK).entity(tvSerieList).build();
    }

}
