package com.eskcti.algafoodapi.api.model;

import com.eskcti.algafoodapi.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenModel {
    @JsonView(RestaurantView.Summary.class)
    private Long id;
    @JsonView(RestaurantView.Summary.class)
    private String name;
}
