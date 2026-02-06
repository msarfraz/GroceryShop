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

    @Order(2)
    @Test
    public void testGetOrder() {
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/newOrderResponse.json"));
        given()
                .contentType(ContentType.JSON)
                .when().get("api/v1/orders/1")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("", equalTo(expectedJson.get()));
    }
    @Order(1)
    @Test
    public void testSaveOrder() {
        JsonPath expectedJson = new JsonPath(new File("src/test/resources/newOrderResponse.json"));
        given()
                .contentType(ContentType.JSON)
        .body("[\n" +
                "  {\n" +
                "    \"code\": \"CE\",\n" +
                "    \"quantity\": 10\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"HM\",\n" +
                "    \"quantity\": 14\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"SS\",\n" +
                "    \"quantity\": 3\n" +
                "  }\n" +
                "]")
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
