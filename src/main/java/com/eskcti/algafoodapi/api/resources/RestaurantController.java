package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.model.KitchenModel;
import com.eskcti.algafoodapi.core.validation.ValidationException;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.api.model.RestaurantModel;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/restaurants", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    SmartValidator validator;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantService.list();
    }

    @GetMapping("/{id}")
    public RestaurantModel find(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.find(id);

        return toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel insert(@RequestBody @Valid Restaurant restaurant) {
        try {
            return toModel(restaurantService.save(restaurant));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestaurantModel update(@PathVariable Long id, @RequestBody @Valid Restaurant restaurant) {
        Restaurant restaurantUpdated = restaurantService.find(id);
        BeanUtils.copyProperties(restaurant, restaurantUpdated, "id", "paymentTypes", "address", "createdAt");
        try {
            return toModel(restaurantService.save(restaurantUpdated));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        restaurantService.remove(id);
    }

    @PatchMapping("/{restaurantId}")
    public RestaurantModel updatePartial(
            @PathVariable Long restaurantId,
            @RequestBody Map<String, Object> fields,
            HttpServletRequest request
    ) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        merge(fields, restaurant, request);
        validate(restaurant, "restaurant");

        return update(restaurantId, restaurant);
    }

    private void validate(Restaurant restaurant, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);
        validator.validate(restaurant, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }

    private void merge(Map<String, Object> fieldsSource, Restaurant restaurantTarget, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurant restaurantSource = objectMapper.convertValue(fieldsSource, Restaurant.class);

            fieldsSource.forEach((name, value) -> {
                Field field = ReflectionUtils.findField(Restaurant.class, name);
                field.setAccessible(true);

                Object newValue = ReflectionUtils.getField(field, restaurantSource);

                ReflectionUtils.setField(field, restaurantSource, newValue);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }

    private RestaurantModel toModel(Restaurant restaurant) {
        KitchenModel kitchenModel = new KitchenModel();
        kitchenModel.setId(restaurant.getKitchen().getId());
        kitchenModel.setName(restaurant.getKitchen().getName());

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(restaurant.getId());
        restaurantModel.setName(restaurant.getName());
        restaurantModel.setShippingFee(restaurant.getShippingFee());
        restaurantModel.setKitchen(kitchenModel);

        return restaurantModel;
    }

    private List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(restaurant -> toModel(restaurant))
                .collect(Collectors.toList());
    }
}
