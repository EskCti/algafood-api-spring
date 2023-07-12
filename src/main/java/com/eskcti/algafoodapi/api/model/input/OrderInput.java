package com.eskcti.algafoodapi.api.model.input;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInput {
    @NotNull
    private RestaurantIdInput restaurant;

    @NotNull
    private PaymentTypeIdInput paymentType;

    @NotNull
    private AddressInput addressDelivery;

    @NotNull
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Size(min = 1)
    private List<OrderItemInput> items;
}
