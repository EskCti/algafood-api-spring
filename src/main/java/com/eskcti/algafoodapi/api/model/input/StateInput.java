package com.eskcti.algafoodapi.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateInput {

    @NotNull
    private String name;
}
