package com.eskcti.algafoodapi.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissions")
@Getter
@Setter
public class PermissionModel extends RepresentationModel<PermissionModel> {
    @Schema(description = "ID da permissão", example = "1")
    private Long id;
    @Schema(description = "Name da permissão", example = "EDITAR_PERMISSAO")
    private String name;
    @Schema(description = "Descrição da permissão", example = "Permissão de editar permissão")
    private String description;
}
