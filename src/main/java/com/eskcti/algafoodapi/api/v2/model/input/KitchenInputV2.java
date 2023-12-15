package com.eskcti.algafoodapi.api.v2.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenInputV2 {

    @NotNull
    private String name;
}
