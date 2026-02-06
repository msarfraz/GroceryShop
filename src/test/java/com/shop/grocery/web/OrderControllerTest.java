package com.shop.grocery.web;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.path.json.JsonPath;
import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }


    @Test
    public void testGetOrder() {
        when().get("api/v1/orders/1")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("id", equalTo(1));
    }

    @Test
    public void testSaveOrder() {
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/newOrder.json"));
        given()
                .contentType(ContentType.JSON)
        .body("{}")
                .when().post("api/v1/orders/")
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(
                        "", equalTo(expectedJson.get()));
    }



}
