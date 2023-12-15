package com.eskcti.algafoodapi.api.v2.model;

import com.eskcti.algafoodapi.api.v1.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "kitchens")
@Getter
@Setter
public class KitchenModelV2 extends RepresentationModel<KitchenModelV2> {
    @JsonView(RestaurantView.Summary.class)
    private Long id;
    @JsonView(RestaurantView.Summary.class)
    private String name;
}
