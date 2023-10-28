package com.eskcti.algafoodapi.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Representa uma forma de pagamento input")
@Getter
@Setter
public class PaymentTypeInput {

    @Schema(example = "Cheque")
    @NotNull
    @NotBlank
    private String description;
}
