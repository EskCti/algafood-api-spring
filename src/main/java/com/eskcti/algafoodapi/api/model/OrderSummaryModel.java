package com.eskcti.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderSummaryModel {
    private String code;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal valueTotal;
    private OffsetDateTime createdAt;
    private RestaurantSummaryModel restaurant;
//    private UserModel customer;
    private String nameCustomer;
}
