package com.infernalwhaler.microservices;

import com.infernalwhaler.microservices.model.Movie;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sdeseure
 * @project quarkus-web-interface
 * @date 14/10/2025
 */

@Path("/api/movies")
@Tag(name = "Movie REST Endpoint")
public class MovieResource {

    private List<Movie> movies = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all movies", description = "Get all Movies listed", operationId = "getMovies")
    @APIResponse(responseCode = "200", description = "Operation GET completed", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response getMovies() {
        return Response.ok(movies).build();
    }

    @GET()
    @Path("/size")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Count movies", description = "Get total size of movies", operationId = "countMovies")
    @APIResponse(responseCode = "200", description = "Operation GET completed", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Integer getSizeMovies() {
        return movies.size();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new movie", description = "Add a new movie to the list", operationId = "addMovie")
    @APIResponse(responseCode = "200", description = "Operation POST completed", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response addMovie(@RequestBody(description = "Movie to Create") Movie movie) {
        movies.add(movie);
        return Response.status(Response.Status.CREATED).entity(movie).build();
    }

    @PUT
    @Path("/{id}/{title}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Update a movie", description = "Update an existing movie", operationId = "updateMovie")
    @APIResponse(responseCode = "200", description = "Operation PUT completed", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response updateMovie(@Parameter(description = "Movie id") @PathParam("id") Long id,
                                @Parameter(description = "Movie Title") @PathParam("title") String title) {
        movies = movies.stream()
                .peek(movie -> {
                    if (movie.getId().equals(id)) {
                        movie.setTitle(title);
                    }
                }).toList();
        return Response.ok(movies).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a movie", description = "Delete an existing movie", operationId = "deleteMovie")
    @APIResponse(responseCode = "200", description = "Operation DELETE completed", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response deleteMovie(@Parameter(description = "Movie Id") @PathParam("id") Long id) {
        boolean removed = false;
        final Optional<Movie> movieToDelete = movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
        if (movieToDelete.isPresent()) {
            removed = movies.remove(movieToDelete.get());
        }
        return removed ? Response.noContent().build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

}
