package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.input.RestaurantInput;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput) {
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

}
