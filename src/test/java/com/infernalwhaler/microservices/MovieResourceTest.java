package com.infernalwhaler.microservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infernalwhaler.microservices.model.Movie;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * @author Sdeseure
 * @project quarkus-web-interface
 * @date 14/10/2025
 */

@QuarkusTest
class MovieResourceTest {

    @Test
    void getMovies() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/api/movies")
                .then()
                .statusCode(200);

    }

    @Test
    void getSizeMovies() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/api/movies/size")
                .then()
                .statusCode(200);
    }


    @Test
    void addMovie() throws JsonProcessingException {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(new ObjectMapper().writeValueAsString(new Movie(1L, "Film")))
                .when()
                .post("/api/movies")
                .then()
                .statusCode(201)
                .body("id", is(1))
                .body("title", is("Film"));
    }
}