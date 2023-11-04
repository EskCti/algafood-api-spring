package com.eskcti.algafoodapi.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Representa um grupo input")
@Getter
@Setter
public class GroupInput {

    @Schema(example = "Pizzsa grande")
    @NotNull
    private String name;
}
