package com.eskcti.algafoodapi;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import com.eskcti.algafoodapi.utils.DatabaseCleaner;
import com.eskcti.algafoodapi.utils.ResourceUtils;
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
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@Sql(scripts = "/_import.sql")
class RegisterKitchenIT {

	public static final int KITCHEN_ID_INDIANA = 2;
	public static final String NAME_KITCHEN_INDIANA = "Indiana";
	public static final int KITCHEN_ID_NOT_FOUND = 99;
	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private KitchenRepository kitchenRepository;

	private Kitchen kitchenIndiana;
	private Integer quantityKitchenRegister;
	private String jsonCorrectKitchenChines;

//	@Autowired
//	private Flyway flyway;

	@BeforeEach
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/kitchens";

		quantityKitchenRegister = (int) kitchenRepository.count();

		jsonCorrectKitchenChines = ResourceUtils.getContentFromResource(
				"/json/correct/kitchen-chinesa.json");

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
	public void shouldReturnQuantityCorrectOfKitchens_whenQueryingKitchens() {
		given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.body("name", hasSize(quantityKitchenRegister));
	}

	@Test
	public void shouldReturnStatus201_whenRegisterKitchen() {
		given()
				.body(jsonCorrectKitchenChines)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void shouldReturnCorrectAnswerAndStatus_whenQueryingExistingKitchen() {
		given()
				.pathParam("kitchenId", KITCHEN_ID_INDIANA)
				.accept(ContentType.JSON)
			.when()
				.get("/{kitchenId}")
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("name", equalTo(NAME_KITCHEN_INDIANA));
	}

	@Test
	public void shouldReturnStatus404_whenQueryingNotExistingKitchen() {
		given()
				.pathParam("kitchenId", KITCHEN_ID_NOT_FOUND)
				.accept(ContentType.JSON)
			.when()
				.get("/{kitchenId}")
			.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void populateKitchen() {
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName(NAME_KITCHEN_INDIANA);
		kitchenRepository.save(kitchen1);

		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Chines");
		kitchenRepository.save(kitchen2);
	}
}
