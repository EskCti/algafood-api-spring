package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.KitchenModel;
import com.eskcti.algafoodapi.api.model.RestaurantModel;
import com.eskcti.algafoodapi.api.model.input.RestaurantInput;
import com.eskcti.algafoodapi.core.validation.ValidationException;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RestaurantModelAssemblier {

    @Autowired
    private ModelMapper modelMapper;
    public RestaurantModel toModel(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantModel.class);
    }

    public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> toModel(restaurant))
                .collect(Collectors.toList());
    }
}
