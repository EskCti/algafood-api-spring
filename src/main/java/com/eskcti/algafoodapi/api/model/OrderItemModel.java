package com.eskcti.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {

    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal priceUnit;
    private BigDecimal priceTotal;

    private String observation;
}
