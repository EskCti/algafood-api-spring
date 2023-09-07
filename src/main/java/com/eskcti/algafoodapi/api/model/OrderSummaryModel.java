package com.eskcti.algafoodapi.api.model;

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
    private String code;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal valueTotal;
    private OffsetDateTime createdAt;
    private RestaurantSummaryModel restaurant;
    private UserModel customer;
    private String nameCustomer;
}
