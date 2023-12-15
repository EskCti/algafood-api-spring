package com.eskcti.algafoodapi.api.v1.assembliers;

import com.eskcti.algafoodapi.api.v1.model.input.CityInput;
import com.eskcti.algafoodapi.domain.models.City;
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
