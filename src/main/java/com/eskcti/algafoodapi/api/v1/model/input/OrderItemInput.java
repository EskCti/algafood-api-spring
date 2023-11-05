package com.eskcti.algafoodapi.api.v1.model.input;

import com.eskcti.algafoodapi.domain.models.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemInput {
    @Schema(example = "1")
    @NotNull
    private Long productId;

    @Schema(example = "2")
    @NotNull
    @Min(value = 1)
    private Integer quantity;

    @Schema(example = "Menos picante, por favor")
    private String observation;
    @ManyToOne
    private Order order;

}
