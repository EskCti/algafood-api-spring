package com.eskcti.algafoodapi.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "orders")
@Getter
@Setter
public class OrderSummaryModel extends RepresentationModel<OrderSummaryModel> {
    @Schema(example = "04813f77-79b5-11ec-9a17-0242ac1b0002")
    private String code;

    @Schema(example = "298.90")
    private BigDecimal subtotal;

    @Schema(example = "10.00")
    private BigDecimal shippingFee;

    @Schema(example = "308.90")
    private BigDecimal valueTotal;

    @Schema(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime createdAt;
    private RestaurantSummaryModel restaurant;
    private UserModel customer;
    private String nameCustomer;
}
