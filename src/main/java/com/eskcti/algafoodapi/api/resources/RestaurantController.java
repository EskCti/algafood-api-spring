package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.RestaurantInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.RestaurantModelAssemblier;
import com.eskcti.algafoodapi.api.model.RestaurantModel;
import com.eskcti.algafoodapi.api.model.input.RestaurantInput;
import com.eskcti.algafoodapi.api.model.view.RestaurantView;
import com.eskcti.algafoodapi.core.validation.ValidationException;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.exceptions.RestaurantNotFoundException;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.services.RestaurantService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurants", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantModelAssemblier modelAssemblier;

    @Autowired
    private RestaurantInputDisassembler inputDisassembler;

    @Autowired
    private SmartValidator validator;

    @GetMapping
    public List<RestaurantModel> list() {
        return modelAssemblier.toCollectionModel(restaurantService.list());
    }

    @JsonView(RestaurantView.Summary.class)
    @GetMapping(params = "project=summary")
    public List<RestaurantModel> listSummary() {
        return modelAssemblier.toCollectionModel(restaurantService.list());
    }

    @GetMapping(params = "project=wrapper")
    public MappingJacksonValue listMapping() {
        List<Restaurant> restaurants = restaurantService.list();
        List<RestaurantModel> restaurantModels = modelAssemblier.toCollectionModel(restaurants);

        MappingJacksonValue restaurantsWrappers = new MappingJacksonValue(restaurantModels);

        restaurantsWrappers.setSerializationView(RestaurantView.Summary.class);

        return restaurantsWrappers;
    }

    @GetMapping("/{id}")
    public RestaurantModel find(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.find(id);

        return modelAssemblier.toModel(restaurant);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantModel insert(@RequestBody @Valid RestaurantInput restaurantInput) {
        try {
            Restaurant restaurant = inputDisassembler.toDomainObject(restaurantInput);

            return modelAssemblier.toModel(restaurantService.save(restaurant));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestaurantModel update(@PathVariable Long id, @RequestBody @Valid RestaurantInput restaurantInput) {
        try {
//            Restaurant restaurant = inputDisassembler.toDomainObject(restaurantInput);

            Restaurant restaurantUpdated = restaurantService.find(id);

            inputDisassembler.copyToDomainObject(restaurantInput, restaurantUpdated);
//            BeanUtils.copyProperties(restaurant, restaurantUpdated, "id", "paymentTypes", "address", "createdAt");
            return modelAssemblier.toModel(restaurantService.save(restaurantUpdated));
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

        return modelAssemblier.toModel(restaurantService.save(restaurant));
    }

    @PutMapping("/{restaurantId}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate (@PathVariable Long restaurantId){
        restaurantService.activate(restaurantId);
    }

    @DeleteMapping("/{restaurantId}/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate (@PathVariable Long restaurantId){
        restaurantService.deactivate(restaurantId);
    }

    @PutMapping("/activate-multiples")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateMultiples(@RequestBody List<Long> restaurantsIds) {
        try {
            restaurantService.activate(restaurantsIds);
        } catch (ConstraintViolationException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/deactivate-multiples")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateMultiples(@RequestBody List<Long> restaurantsIds) {
        try {
            restaurantService.deactivate(restaurantsIds);
        } catch (ConstraintViolationException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restaurantId}/opening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void opening (@PathVariable Long restaurantId) { restaurantService.opening(restaurantId); }

    @PutMapping("/{restaurantId}/closing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closing (@PathVariable Long restaurantId) { restaurantService.closing(restaurantId); }

    public void validate(Restaurant restaurant, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);
        validator.validate(restaurant, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }

    public void merge(Map<String, Object> fieldsSource, Restaurant restaurantTarget, HttpServletRequest request) {
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
