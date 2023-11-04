package com.eskcti.algafoodapi.api.model;

import com.eskcti.algafoodapi.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class RestaurantModel extends RepresentationModel<RestaurantModel> {
    @Schema(example = "1")
    @JsonView(RestaurantView.Summary.class)
    private Long id;

    @Schema(example = "Thai Gourmet")
    @JsonView(RestaurantView.Summary.class)
    private String name;

    @Schema(example = "12.00")
    @JsonView(RestaurantView.Summary.class)
    private BigDecimal shippingFee;

    @JsonView(RestaurantView.Summary.class)
    private KitchenModel kitchen;

    private Boolean active;
    private Boolean open;
    private AddressModel address;

}
