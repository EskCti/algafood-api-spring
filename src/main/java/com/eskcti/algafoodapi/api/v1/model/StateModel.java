package com.eskcti.algafoodapi.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "states")
@Schema(name = "Estado", description = "Representa um estado")
@Getter
@Setter
public class StateModel extends RepresentationModel<StateModel> {
    @Schema(description = "ID do estado", example = "1")
    private Long id;
    @Schema(example = "SP")
    private String name;
}
