package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.assembliers.StateInputDisassembler;
import com.eskcti.algafoodapi.api.v1.assembliers.StateModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.StateModel;
import com.eskcti.algafoodapi.api.v1.model.input.StateInput;
import com.eskcti.algafoodapi.api.v1.openapi.StateControllerOpenApi;
import com.eskcti.algafoodapi.core.security.CheckSecutiry;
import com.eskcti.algafoodapi.domain.models.State;
import com.eskcti.algafoodapi.domain.services.StateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/states", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StateController implements StateControllerOpenApi {
    @Autowired
    private StateService stateService;

    @Autowired
    private StateModelAssemblier modelAssemblier;

    @Autowired
    private StateInputDisassembler inputDisassembler;

    @CheckSecutiry.States.CanConsult
    @GetMapping
    public CollectionModel<StateModel> list() {
        List<State> stateList = stateService.list();
        return modelAssemblier.toCollectionModel(stateList);
    }

    @CheckSecutiry.States.CanConsult
    @GetMapping("/{id}")
    public StateModel find(@PathVariable Long id) {

        return modelAssemblier.toModel(stateService.find(id));
    }

    @CheckSecutiry.States.CanEdit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateModel insert(@RequestBody @Valid StateInput stateInput) {
        State state = inputDisassembler.toDomainObject(stateInput);
        return modelAssemblier.toModel(stateService.save(state));
    }

    @CheckSecutiry.States.CanEdit
    @PutMapping("/{id}")
    public StateModel update(@PathVariable Long id, @RequestBody @Valid StateInput stateInput) {
        State stateUpdate = stateService.find(id);
        inputDisassembler.copyToDomainObject(stateInput, stateUpdate);
//        BeanUtils.copyProperties(state, stateUpdate, "id");
        return modelAssemblier.toModel(stateService.save(stateUpdate));
    }

    @CheckSecutiry.States.CanEdit
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        stateService.remove(id);
    }
}
