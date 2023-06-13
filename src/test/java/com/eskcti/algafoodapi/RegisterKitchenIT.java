package com.eskcti.algafoodapi;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import com.eskcti.algafoodapi.utils.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@Sql(scripts = "/_import.sql")
class RegisterKitchenIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private KitchenRepository kitchenRepository;

//	@Autowired
//	private Flyway flyway;

	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/kitchens";

//		flyway.migrate();

//		databaseCleaner.clearTables();
//		populateKtchen();
	}

	@Test
	public void shouldReturnStatus200_whenQueryKitchen() {
			given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void souldContainFourKitchens_whenQueryingKitchens() {
		given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.body("name", hasSize(3))
				.body("name", hasItems("Indiana", "Chines"));

	}

	@Test
	public void shouldReturnStatus201_whenRegisterKitchen() {
		given()
				.body("{\"name\": \"Chinesa\"}")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	private void populateKtchen() {
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Indiana");
		kitchenRepository.save(kitchen1);

		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Chines");
		kitchenRepository.save(kitchen2);
	}
}
