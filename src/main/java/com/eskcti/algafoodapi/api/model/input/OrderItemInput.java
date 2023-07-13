package com.eskcti.algafoodapi.api.model.input;

import com.eskcti.algafoodapi.domain.models.Order;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemInput {
    @NotNull
    private Long productId;
    @NotNull
    @Min(value = 1)
    private Integer quantity;
    private String observation;
    @ManyToOne
    private Order order;

}
