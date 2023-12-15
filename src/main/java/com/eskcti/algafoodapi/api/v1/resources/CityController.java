package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.ResourceUriHelper;
import com.eskcti.algafoodapi.api.v1.assembliers.CityInputDisassembler;
import com.eskcti.algafoodapi.api.v1.assembliers.CityModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.CityModel;
import com.eskcti.algafoodapi.api.v1.model.input.CityInput;
import com.eskcti.algafoodapi.api.v1.openapi.CityControllerOpenApi;
import com.eskcti.algafoodapi.core.security.CheckSecutiry;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.StateNotFoundException;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityControllerOpenApi {
    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelAssemblier modelAssemblier;

    @Autowired
    private CityInputDisassembler inputDisassembler;

    @CheckSecutiry.Cities.CanConsult
    @GetMapping
    public CollectionModel<CityModel> list() {
        List<City> cityList = cityService.list();
        return modelAssemblier.toCollectionModel(cityList);
    }

    @CheckSecutiry.Cities.CanConsult
    @GetMapping("/{id}")
    public CityModel find(
            @PathVariable Long id
    ) {
        return modelAssemblier.toModel(cityService.find(id));
    }

    @CheckSecutiry.Cities.CanEdit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel insert(
            @RequestBody @Valid CityInput cityInput
    ) {
        try {
            City city = inputDisassembler.toDomainObject(cityInput);
            CityModel cityModel = modelAssemblier.toModel(cityService.save(city));

            ResourceUriHelper.addUriInResponseHeader(cityModel.getId());

            return cityModel;
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @CheckSecutiry.Cities.CanEdit
    @PutMapping("/{id}")
    public CityModel update(
            @PathVariable Long id,
            @RequestBody @Valid CityInput cityInput
    ) {
        try {
            City cityUpdate = cityService.find(id);
//            BeanUtils.copyProperties(city, cityUpdate, "id");
            inputDisassembler.copyToDomainObject(cityInput, cityUpdate);
            return modelAssemblier.toModel(cityService.save(cityUpdate));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @CheckSecutiry.Cities.CanEdit
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {
        cityService.remove(id);
    }
}
