package com.eskcti.algafoodapi.core.modelmaper;

import com.eskcti.algafoodapi.api.v1.model.AddressModel;
import com.eskcti.algafoodapi.domain.models.Address;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
//        modelMapper().createTypeMap(Restaurant.class, RestaurantModel.class)
//                .addMapping(Restaurant::getShippingFee, RestaurantModel::setShippingFee);

        var addressToAddressModelTypeMap = modelMapper.createTypeMap(
                Address.class, AddressModel.class
        );

        addressToAddressModelTypeMap.<String>addMapping(
                addressSrc -> addressSrc.getCity().getState().getName(),
                ((addressModelTarget, value) -> addressModelTarget.getCity().setState(value)));

        return modelMapper;
    }
}
