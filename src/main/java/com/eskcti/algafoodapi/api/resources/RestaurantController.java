package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurants", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantService.list();
    }

    @GetMapping("/{id}")
    public Restaurant find(@PathVariable Long id) {
        return restaurantService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant insert(@RequestBody @Valid Restaurant restaurant) {
        try {
            return restaurantService.save(restaurant);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Restaurant restaurantUpdated = restaurantService.find(id);
        BeanUtils.copyProperties(restaurant, restaurantUpdated, "id", "paymentTypes", "address", "createdAt");
        try {
            return restaurantService.save(restaurantUpdated);
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
    public Restaurant updatePartial(
            @PathVariable Long restaurantId,
            @RequestBody Map<String, Object> fields,
            HttpServletRequest request
    ) {
        Restaurant restaurant = restaurantService.find(restaurantId);

        merge(fields, restaurant, request);

        return update(restaurantId, restaurant);
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
}
