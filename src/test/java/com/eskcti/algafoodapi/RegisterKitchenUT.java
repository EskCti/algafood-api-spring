package com.eskcti.algafoodapi;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.KitchenNotFoundException;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.services.KitchenService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RegisterKitchenUT {

	@Autowired
	KitchenService kitchenService;

	@Test
	public void shouldSucessfully_ToRegisterKitchen_WhenWithNameCorrectly() {
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName("Chinesa");

		newKitchen = kitchenService.save(newKitchen);

		assertThat(newKitchen, notNullValue());
		assertThat(newKitchen.getId(), notNullValue());
	}

	@Test
	public void shouldFail_ToRegisterKitchen_WhenWithoutName() {
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName(null);

		assertThrows(ConstraintViolationException.class, () -> kitchenService.save(newKitchen));
	}

	@Test
	public void shouldFail_toRemoveKitchen_WhenKitchenInUse() {
		assertThrows(EntityInUseException.class, () -> kitchenService.remove(1L));
	}

	@Test
	public void shouldFail_toRemoveKitchen_whenKitchenNotFound() {
		assertThrows(KitchenNotFoundException.class, () -> kitchenService.remove(9999L));
	}
}
