package com.eskcti.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class StateNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long stateId) {
        this(String.format("Not found State with id %d", stateId));
    }
}
