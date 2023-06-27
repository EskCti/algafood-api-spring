package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.input.CityInput;
import com.eskcti.algafoodapi.api.model.input.RestaurantInput;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.models.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public City toDomainObject(CityInput cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInput cityInput, City city) {
        city.setState(new State());
        modelMapper.map(cityInput, city);
    }

}
