package com.eskcti.algafoodapi.api.v2.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenIdInputV2 {

    @NotNull
    private Long id;
}
