package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.StateInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.StateModelAssemblier;
import com.eskcti.algafoodapi.api.model.StateModel;
import com.eskcti.algafoodapi.api.model.input.StateInput;
import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.State;
import com.eskcti.algafoodapi.domain.services.StateService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/states", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StateController {
    @Autowired
    private StateService stateService;

    @Autowired
    private StateModelAssemblier modelAssemblier;

    @Autowired
    private StateInputDisassembler inputDisassembler;

    @GetMapping
    public List<State> list() {
        return stateService.list();
    }

    @GetMapping("/{id}")
    public StateModel find(@PathVariable Long id) {

        return modelAssemblier.toModel(stateService.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateModel insert(@RequestBody @Valid StateInput stateInput) {
        State state = inputDisassembler.toDomainObject(stateInput);
        return modelAssemblier.toModel(stateService.save(state));
    }

    @PutMapping("/{id}")
    public StateModel update(@PathVariable Long id, @RequestBody @Valid StateInput stateInput) {
        State stateUpdate = stateService.find(id);
        inputDisassembler.copyToDomainObject(stateInput, stateUpdate);
//        BeanUtils.copyProperties(state, stateUpdate, "id");
        return modelAssemblier.toModel(stateService.save(stateUpdate));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stateService.remove(id);
    }
}
