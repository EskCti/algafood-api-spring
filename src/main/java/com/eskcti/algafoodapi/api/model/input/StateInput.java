package com.eskcti.algafoodapi.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Representa um estado input")
@Getter
@Setter
public class StateInput {

    @Schema(example = "SP")
    @NotNull
    private String name;
}
