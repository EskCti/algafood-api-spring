package com.eskcti.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class RestaurantSummaryModel extends RepresentationModel<RestaurantSummaryModel> {
    private Long id;
    private String name;
}
