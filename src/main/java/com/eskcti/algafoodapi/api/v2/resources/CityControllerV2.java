package com.eskcti.algafoodapi.api.v2.resources;

import com.eskcti.algafoodapi.api.ResourceUriHelper;
import com.eskcti.algafoodapi.api.v2.assembliers.CityInputDisassemblerV2;
import com.eskcti.algafoodapi.api.v2.assembliers.CityModelAssemblierV2;
import com.eskcti.algafoodapi.api.v2.model.CityModelV2;
import com.eskcti.algafoodapi.api.v2.model.input.CityInputV2;
import com.eskcti.algafoodapi.api.v2.openapi.CityControllerOpenApiV2;
import com.eskcti.algafoodapi.core.web.AlgaMediaTypes;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.StateNotFoundException;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.services.CityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cities", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
public class CityControllerV2 implements CityControllerOpenApiV2 {
    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelAssemblierV2 modelAssemblier;

    @Autowired
    private CityInputDisassemblerV2 inputDisassembler;

    @GetMapping
    public CollectionModel<CityModelV2> list() {
        List<City> cityList = cityService.list();
        return modelAssemblier.toCollectionModel(cityList);
    }

    @GetMapping("/{id}")
    public CityModelV2 find(
            @PathVariable Long id
    ) {
        return modelAssemblier.toModel(cityService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModelV2 insert(
            @RequestBody @Valid CityInputV2 cityInput
    ) {
        try {
            City city = inputDisassembler.toDomainObject(cityInput);
            CityModelV2 cityModel = modelAssemblier.toModel(cityService.save(city));

            ResourceUriHelper.addUriInResponseHeader(cityModel.getId());

            return cityModel;
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public CityModelV2 update(
            @PathVariable Long id,
            @RequestBody @Valid CityInputV2 cityInput
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {
        cityService.remove(id);
    }
}
