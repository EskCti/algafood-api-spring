package com.eskcti.algafoodapi.api.v2.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityIdInputV2 {

    @Schema(example = "1")
    @NotNull
    private Long id;
}
