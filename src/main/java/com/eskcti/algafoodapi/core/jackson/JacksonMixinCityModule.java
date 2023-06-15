package com.eskcti.algafoodapi.core.jackson;

import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.api.model.mixins.CityMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinCityModule extends SimpleModule {
    private static final long serialVersionUID = 1l;

    public JacksonMixinCityModule() {
        setMixInAnnotation(City.class, CityMixin.class);
    }
}
