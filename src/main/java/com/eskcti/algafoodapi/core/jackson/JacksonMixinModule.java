package com.eskcti.algafoodapi.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
    private static final long serialVersionUID = 1l;

    public JacksonMixinModule() {
//        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
    }
}
