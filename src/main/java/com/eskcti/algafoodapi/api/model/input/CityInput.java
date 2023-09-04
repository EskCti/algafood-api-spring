package com.eskcti.algafoodapi.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Representa uma cidade input")
@Getter
@Setter
public class CityInput {

    @Schema(example = "São Paulo")
    @NotBlank
    private String name;


    @Valid
    @NotNull
    private StateIdInput state;
}
