package com.eskcti.algafoodapi;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterKitchenIT {

	@LocalServerPort
	private int port;
	@Test
	public void shouldReturnStatus200_whenQueryKitchen() {
			given()
				.basePath("/kitchens")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void souldContainFourKitchens_whenQueryingKitchens() {
		given()
				.basePath("/kitchens")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.body("name", hasSize(12))
				.body("name", hasItems("Indiana", "Chines"));

	}
}
