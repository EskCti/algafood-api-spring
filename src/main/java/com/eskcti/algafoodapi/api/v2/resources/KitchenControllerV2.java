package com.eskcti.algafoodapi.api.v2.resources;

import com.eskcti.algafoodapi.api.v1.model.KitchenModel;
import com.eskcti.algafoodapi.api.v1.model.input.KitchenInput;
import com.eskcti.algafoodapi.api.v1.openapi.KitchenControllerOpenApi;
import com.eskcti.algafoodapi.api.v2.assembliers.KitchenInputDisassemblerV2;
import com.eskcti.algafoodapi.api.v2.assembliers.KitchenModelAssemblierV2;
import com.eskcti.algafoodapi.api.v2.model.KitchenModelV2;
import com.eskcti.algafoodapi.api.v2.model.input.KitchenInputV2;
import com.eskcti.algafoodapi.api.v2.openapi.KitchenControllerOpenApiV2;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.services.KitchenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v2/kitchens", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class KitchenControllerV2 implements KitchenControllerOpenApiV2 {
    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenModelAssemblierV2 modelAssemblier;

    @Autowired
    private KitchenInputDisassemblerV2 inputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<KitchenModelV2> list(@PageableDefault(size = 24) Pageable pageable) {
        Page<Kitchen> kitchensPage = kitchenService.list(pageable);

        PagedModel<KitchenModelV2> kitchenModelPagedModel = pagedResourcesAssembler
                .toModel(kitchensPage, modelAssemblier);

        return kitchenModelPagedModel;
    }

    @GetMapping("/{id}")
    public KitchenModelV2 find(@PathVariable Long id) {

        Kitchen kitchen = kitchenService.find(id);

        return modelAssemblier.toModel(kitchen);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenModelV2 save(@RequestBody @Valid KitchenInputV2 kitchenInput) {
        try {
            Kitchen kitchen = inputDisassembler.toDomainObject(kitchenInput);
            return modelAssemblier.toModel(kitchenService.save(kitchen));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public KitchenModelV2 update(@PathVariable Long id, @RequestBody @Valid KitchenInputV2 kitchenInput) {
//        Kitchen kitchen = modelAssemblier.toDomainObject(kitchenInput);

        Kitchen kitchenUpdate = kitchenService.find(id);
        inputDisassembler.copyToDomainObject(kitchenInput, kitchenUpdate);
//        BeanUtils.copyProperties(kitchen, kitchenUpdate, "id");
        return modelAssemblier.toModel(kitchenService.save(kitchenUpdate));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        kitchenService.find(id);
        kitchenService.remove(id);
    }
}
