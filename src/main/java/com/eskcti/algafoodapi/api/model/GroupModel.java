package com.eskcti.algafoodapi.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "groups")
@Schema(description = "Representa um grupo")
@Getter
@Setter
public class GroupModel extends RepresentationModel<GroupModel> {
    @Schema(description = "ID do grupo", example = "1")
    private Long id;

    @Schema(example = "Pizza grande")
    private String name;
}
