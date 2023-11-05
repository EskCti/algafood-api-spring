package com.eskcti.algafoodapi.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "products")
@Getter
@Setter
public class ProductModel extends RepresentationModel<ProductModel> {
    @Schema(example = "1")
    private Long id;
    @Schema(example = "Espetinho de Cupim")
    private String name;
    @Schema(example = "Acompanha farinha, mandioca e vinagrete")
    private String description;
    @Schema(example = "12.50")
    private BigDecimal price;
    @Schema(example = "true")
    private Boolean active;
}
