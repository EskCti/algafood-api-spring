package com.eskcti.algafoodapi.api.resources;

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

    @GetMapping
    public List<State> list() {
        return stateService.list();
    }

    @GetMapping("/{id}")
    public State find(@PathVariable Long id) {
        return stateService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State insert(@RequestBody @Valid State state) {
        return stateService.save(state);
    }

    @PutMapping("/{id}")
    public State update(@PathVariable Long id, @RequestBody @Valid State state) {
        State stateUpdate = stateService.find(id);
        BeanUtils.copyProperties(state, stateUpdate, "id");
        return stateService.save(stateUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stateService.remove(id);
    }
}
