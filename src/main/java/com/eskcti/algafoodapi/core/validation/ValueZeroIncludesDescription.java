package com.eskcti.algafoodapi.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = { ValueZeroIncludesDescriptionValidator.class }
)
public @interface ValueZeroIncludesDescription {
    String message() default "description required inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String valueField();
    String descriptionField();
    String descriptionRequired();
}
