package com.eskcti.algafoodapi.api.v2.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Representa uma cidade input")
@Getter
@Setter
public class CityInputV2 {

    @Schema(example = "São Paulo")
    @NotBlank
    private String name;


//    @Valid
//    @NotNull
//    private StateIdInput state;
}
