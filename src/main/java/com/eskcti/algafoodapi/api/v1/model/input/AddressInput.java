package com.eskcti.algafoodapi.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class AddressInput {
    @Schema(example = "38400-000")
    @NotBlank
    private String zipCode;

    @Schema(example = "Rua Floriano Peixoto")
    @NotBlank
    private String publicPlace;

    @Schema(example = "\"1500\"")
    @NotBlank
    private String number;

    @Schema(example = "Apto 901")
    private String complement;

    @Schema(example = "Centro")
    @NotBlank
    private String neighborhood;
    @Valid
    @NotNull
    private CityIdInput city;
}
