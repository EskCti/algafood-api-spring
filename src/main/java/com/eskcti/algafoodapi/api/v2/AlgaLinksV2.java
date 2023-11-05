package com.eskcti.algafoodapi.api.v2;

import com.eskcti.algafoodapi.api.v2.resources.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgaLinksV2 {
    public Link linkToCity(Long cityId) {
        return linkTo(methodOn(CityControllerV2.class)
                .find(cityId)).withSelfRel();
    }

    public Link linkToCities() {
        return linkTo(methodOn(CityControllerV2.class).list())
                .withRel("cities");
    }
}
