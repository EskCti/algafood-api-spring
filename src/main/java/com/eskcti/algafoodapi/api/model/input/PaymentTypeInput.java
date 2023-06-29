package com.eskcti.algafoodapi.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentTypeInput {

    @NotNull
    @NotBlank
    private String description;
}
