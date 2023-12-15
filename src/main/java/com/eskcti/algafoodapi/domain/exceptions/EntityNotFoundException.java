package com.eskcti.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class EntityNotFoundException extends BusinessException {
    private static final long serialVersionUID = 1l;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
