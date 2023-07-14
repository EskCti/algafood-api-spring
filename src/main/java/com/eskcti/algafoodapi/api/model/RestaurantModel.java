package com.eskcti.algafoodapi.api.model;

import com.eskcti.algafoodapi.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel {
    @JsonView(RestaurantView.Summary.class)
    private Long id;

    @JsonView(RestaurantView.Summary.class)
    private String name;

    @JsonView(RestaurantView.Summary.class)
    private BigDecimal shippingFee;

    @JsonView(RestaurantView.Summary.class)
    private KitchenModel kitchen;

    private Boolean active;
    private Boolean open;
    private AddressModel address;

}
