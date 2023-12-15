package com.eskcti.algafoodapi.api.v1.model.mixins;

import com.eskcti.algafoodapi.domain.models.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CityMixin {
    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;
}
