package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.ResourceUriHelper;
import com.eskcti.algafoodapi.api.assembliers.CityInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.CityModelAssemblier;
import com.eskcti.algafoodapi.api.model.CityModel;
import com.eskcti.algafoodapi.api.model.input.CityInput;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.StateNotFoundException;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna lista de cidades")
    )
    public CollectionModel<CityModel> list() {
        List<City> cityList = cityService.list();
        List<CityModel> cityModels = modelAssemblier.toCollectionModel(cityList);

        cityModels.forEach(cityModel -> {
            cityModel.add(linkTo(methodOn(CityController.class)
                    .find(cityModel.getId())).withSelfRel());
            cityModel.add(linkTo(methodOn(CityController.class).list())
                    .withRel("cities"));

            cityModel.getState().add(linkTo(methodOn(StateController.class)
                    .find(cityModel.getState().getId())).withSelfRel());
            cityModel.getState().add(linkTo(methodOn(StateController.class).list())
                    .withRel("states"));
        });

        CollectionModel<CityModel> collectionModel = CollectionModel.of(cityModels);
        collectionModel.add(linkTo(CityController.class).withSelfRel());
        return collectionModel;
    }

    @Operation(summary = "Buscar uma cidade por ID")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso - Retorna a cidade")
    })
    public CityModel find(
            @Parameter(description = "ID da cidade a ser obtido")
            @PathVariable Long id
    ) {

        CityModel cityModel = modelAssemblier.toModel(cityService.find(id));

        cityModel.add(linkTo(methodOn(CityController.class)
                .find(cityModel.getId())).withSelfRel());
        cityModel.add(linkTo(methodOn(CityController.class).list())
                .withRel("cities"));

        cityModel.getState().add(linkTo(methodOn(StateController.class)
                .find(cityModel.getState().getId())).withSelfRel());
        cityModel.getState().add(linkTo(methodOn(StateController.class).list())
                .withRel("states"));

        return cityModel;
    }

    @Operation(summary = "Adicionar nova cidade")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel insert(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma nova cidade")
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

    @Operation(summary = "Atualizar a cidade por ID")
    @PutMapping("/{id}")
    public CityModel update(
            @Parameter(description = "ID da cidade a ser atualizada")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação de uma cidade a ser atualizada")
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

    @Operation(summary = "Excluir a cidade por ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "ID da cidade a ser excluida")
            @PathVariable Long id
    ) {
        cityService.remove(id);
    }
}
