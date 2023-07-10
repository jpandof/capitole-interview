package com.inditex.interview.ws;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class GetProductUseCaseTest {

    @LocalServerPort
    private int port;
    private String url;

    @BeforeEach
    public void setup() {
        String base_uri = "http://localhost";
        String endpoint = "/api/v1/products";
        url = "%s:%d%s".formatted(base_uri, port, endpoint);
    }

    @Test
    void interviewTestOne() {
        given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .body(Matchers.containsString("[{\"id\":5},{\"id\":1},{\"id\":3}]"));
    }

    @Test
    void notFound() {
        given()
            .when()
            .get(url + "/notfound")
            .then()
            .statusCode(404);
    }
}
