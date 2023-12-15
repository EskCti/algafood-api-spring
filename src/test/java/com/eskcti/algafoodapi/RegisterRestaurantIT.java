package com.eskcti.algafoodapi;

import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import com.eskcti.algafoodapi.utils.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@Sql(scripts = "/_import.sql")
public class RegisterRestaurantIT {
    public static final int RESTAURANT_ID_FOUND = 1;
    public static final int RESTAURANT_ID_NOT_FOUND = 99;
    @LocalServerPort
    private int port;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Integer quantityRestaurantRegister;
    private String jsonCorrectRestaurant;

    @BeforeEach
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurants";

        quantityRestaurantRegister = (int) restaurantRepository.count();

        jsonCorrectRestaurant = ResourceUtils.getContentFromResource(
                "/json/correct/restaurant.json");
    }

    @Test
    public void shouldReturnStatus200_whenQueryRestaurant() {
        given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnQuantityCorrectOfRestaurants_whenQueryingRestaurants() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("name", hasSize(quantityRestaurantRegister));
    }

    @Test
    public void shouldReturnCorrectStatus_whenQueryingExistingRestaurant() {
        given()
                .pathParam("kitchenId", RESTAURANT_ID_FOUND)
                .accept(ContentType.JSON)
                .when()
                .get("/{kitchenId}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnStatus404_whenQueryingNotExistingRestaurant() {
        given()
                .pathParam("restaurantId", RESTAURANT_ID_NOT_FOUND)
                .accept(ContentType.JSON)
                .when()
                .get("/{restaurantId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
