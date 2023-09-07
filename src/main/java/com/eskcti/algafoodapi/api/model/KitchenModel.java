package com.eskcti.algafoodapi.api.model;

import com.eskcti.algafoodapi.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "kitchens")
@Getter
@Setter
public class KitchenModel extends RepresentationModel<KitchenModel> {
    @JsonView(RestaurantView.Summary.class)
    private Long id;
    @JsonView(RestaurantView.Summary.class)
    private String name;
}
