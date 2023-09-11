package com.eskcti.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductModel extends RepresentationModel<ProductModel> {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
}
