package com.eskcti.algafoodapi.api.v2;

import com.eskcti.algafoodapi.api.v2.resources.CityControllerV2;
import com.eskcti.algafoodapi.api.v2.resources.KitchenControllerV2;
import org.springframework.hateoas.Link;
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


    public Link linkToKitchen(Long kitchenId) {
        return linkTo(methodOn(KitchenControllerV2.class)
                .find(kitchenId)).withSelfRel();
    }
    public Link linkToKitchens() {
        return linkTo(KitchenControllerV2.class)
                .withRel("kitchens");
    }

}
