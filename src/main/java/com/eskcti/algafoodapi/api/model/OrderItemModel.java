package com.eskcti.algafoodapi.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel {

    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal priceUnit;
    private BigDecimal priceTotal;

    private String observation;
}
