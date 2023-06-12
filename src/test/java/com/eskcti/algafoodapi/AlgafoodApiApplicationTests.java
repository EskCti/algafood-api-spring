package com.eskcti.algafoodapi;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.services.KitchenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class RegisterKitchenIntegrationTests {

	@Autowired
	KitchenService kitchenService;

	@Test
	public void testKitchenRegistrationSuccessfully() {
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName("Chinesa");

		newKitchen = kitchenService.save(newKitchen);

		assertThat(newKitchen, notNullValue());
		assertThat(newKitchen.getId(), notNullValue());
	}
}
