package com.eskcti.algafoodapi.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cities")
@Schema(description = "Representa uma cidade")
@Getter
@Setter
public class CityModel extends RepresentationModel<CityModel> {
    @Schema(description = "ID da cidade", example = "1")
    private Long id;

    @Schema(example = "São Paulo")
    private String name;

    private StateModel state;

}
