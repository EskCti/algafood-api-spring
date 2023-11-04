package com.eskcti.algafoodapi.api.v1.model.input;

import com.eskcti.algafoodapi.core.validation.Groups;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenIdInput {

    @NotNull
    private Long id;
}
