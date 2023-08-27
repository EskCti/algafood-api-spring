package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.CityInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.CityModelAssemblier;
import com.eskcti.algafoodapi.api.model.CityModel;
import com.eskcti.algafoodapi.api.model.input.CityInput;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.StateNotFoundException;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cidades", description = "Gerencia as cidades")
@RestController
@RequestMapping(value = "/cities", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private CityModelAssemblier modelAssemblier;

    @Autowired
    private CityInputDisassembler inputDisassembler;

    @Operation(summary = "Listar as cidades")
    @GetMapping
    public List<City> list() {
        return cityService.list();
    }

    @Operation(summary = "Buscar uma cidade por ID")
    @GetMapping("/{id}")
    public CityModel find(@PathVariable Long id) {

        return modelAssemblier.toModel(cityService.find(id));
    }

    @Operation(summary = "Adicionar nova cidade")
    @PostMapping
    public CityModel insert(@RequestBody @Valid CityInput cityInput) {
        try {
            City city = inputDisassembler.toDomainObject(cityInput);
            return modelAssemblier.toModel(cityService.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Operation(summary = "Atualizar a cidade por ID")
    @PutMapping("/{id}")
    public CityModel update(@PathVariable Long id, @RequestBody @Valid CityInput cityInput) {
        try {
            City cityUpdate = cityService.find(id);
//            BeanUtils.copyProperties(city, cityUpdate, "id");
            inputDisassembler.copyToDomainObject(cityInput, cityUpdate);
            return modelAssemblier.toModel(cityService.save(cityUpdate));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Operation(summary = "Excluir a cidade por ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityService.remove(id);
    }
}
