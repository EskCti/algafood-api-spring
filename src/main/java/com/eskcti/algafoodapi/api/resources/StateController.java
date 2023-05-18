package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.models.State;
import com.eskcti.algafoodapi.domain.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/states", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class StateController {
    @Autowired
    private StateRepository stateRepository;

    @GetMapping
    public List<State> list() {
        return stateRepository.list();
    }
}
