package com.eskcti.algafoodapi.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Porco com molho agridoce")
    private Long productId;

    @Schema(example = "2")
    private Integer quantity;

    @Schema(example = "78.90")
    private BigDecimal priceUnit;

    @Schema(example = "157.80")
    private BigDecimal priceTotal;

    @Schema(example = "Menos picante, por favor")
    private String observation;
}
