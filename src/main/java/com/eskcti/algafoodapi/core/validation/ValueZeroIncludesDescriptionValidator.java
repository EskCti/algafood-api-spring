package com.eskcti.algafoodapi.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class ValueZeroIncludesDescriptionValidator implements ConstraintValidator<ValueZeroIncludesDescription, Object> {

    private String valueField;
    private String descriptionField;
    private String descriptionRequired;

    @Override
    public void initialize(ValueZeroIncludesDescription constraint) {
        this.valueField = constraint.valueField();
        this.descriptionField = constraint.descriptionField();
        this.descriptionRequired = constraint.descriptionRequired();
    }

    @Override
    public boolean isValid(Object objectValidation, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        try {
            BigDecimal value = (BigDecimal) BeanUtils
                    .getPropertyDescriptor(objectValidation.getClass(), valueField)
                    .getReadMethod().invoke(objectValidation);
            String description = (String) BeanUtils
                    .getPropertyDescriptor(objectValidation.getClass(), descriptionField)
                    .getReadMethod().invoke(objectValidation);
            if (value != null && BigDecimal.ZERO.compareTo(value) == 0 && description != null) {
                valid = description.toLowerCase().contains(this.descriptionRequired.toLowerCase());
            }
            return valid;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
