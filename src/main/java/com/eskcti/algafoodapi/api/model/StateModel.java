package com.eskcti.algafoodapi.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Estado", description = "Representa um estado")
@Getter
@Setter
public class StateModel {
    @Schema(description = "ID do estado", example = "1")
    private Long id;
    @Schema(example = "SP")
    private String name;
}
