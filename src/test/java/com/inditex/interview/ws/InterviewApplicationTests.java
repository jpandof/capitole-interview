package com.inditex.interview.ws;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class InterviewApplicationTests {

    @LocalServerPort
    private int port;
    private String url;

    @BeforeEach
    public void setup() {
        String base_uri = "http://localhost";
        url = "%s:%d".formatted(base_uri, port);
    }

    @Test
    void swaggerDocumentation() {
        url = url.concat("/swagger-ui.html");

        given()
            .when()
            .get(url)
            .then()
            .statusCode(200);
    }

    @Test
    void healtcheck() {
        url = url.concat("/healthcheck");

        given()
            .when()
            .get(url)
            .then()
            .statusCode(200);
    }

    @Test
    void notFound() {
        url = url.concat("/notFound")
            .concat("productId=35455&")
            .concat("brandId=1&")
            .concat("time=2020-06-14T21:00:00");

        given()
            .when()
            .get(url)
            .then()
            .statusCode(404);
    }
}
