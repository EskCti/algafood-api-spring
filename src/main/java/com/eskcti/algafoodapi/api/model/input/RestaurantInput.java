package com.eskcti.algafoodapi.api.model.input;

import com.eskcti.algafoodapi.core.validation.Groups;
import com.eskcti.algafoodapi.core.validation.Multiple;
import com.eskcti.algafoodapi.core.validation.ShippingFee;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantInput {

    @NotBlank
    private String name;

    //    @PositiveOrZero
    @ShippingFee
    @Multiple(number = 5)
    private BigDecimal shippingFee;

    @Valid
    @NotNull
//    @ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
    private KitchenIdInput kitchen;

    @Valid
    @NotNull
    private AddressInput address;
}
