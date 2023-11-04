package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.assembliers.KitchenInputDisassembler;
import com.eskcti.algafoodapi.api.v1.assembliers.KitchenModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.KitchenModel;
import com.eskcti.algafoodapi.api.v1.model.input.KitchenInput;
import com.eskcti.algafoodapi.api.v1.openapi.KitchenControllerOpenApi;
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
@RequestMapping(value = "/kitchens", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class KitchenController implements KitchenControllerOpenApi {
    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenModelAssemblier modelAssemblier;

    @Autowired
    private KitchenInputDisassembler inputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<KitchenModel> list(@PageableDefault(size = 24) Pageable pageable) {
        Page<Kitchen> kitchensPage = kitchenService.list(pageable);

        PagedModel<KitchenModel> kitchenModelPagedModel = pagedResourcesAssembler
                .toModel(kitchensPage, modelAssemblier);

        return kitchenModelPagedModel;
    }

    @GetMapping("/{id}")
    public KitchenModel find(@PathVariable Long id) {

        Kitchen kitchen = kitchenService.find(id);

        return modelAssemblier.toModel(kitchen);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenModel save(@RequestBody @Valid KitchenInput kitchenInput) {
        try {
            Kitchen kitchen = inputDisassembler.toDomainObject(kitchenInput);
            return modelAssemblier.toModel(kitchenService.save(kitchen));
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public KitchenModel update(@PathVariable Long id, @RequestBody @Valid KitchenInput kitchenInput) {
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
