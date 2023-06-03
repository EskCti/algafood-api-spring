package com.eskcti.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends ResponseStatusException {
    private static final long serialVersionUID = 1l;

    public EntityNotFoundException(HttpStatusCode status, String message) {
        super(status, message);
    }

    public EntityNotFoundException(String message) {
        this(HttpStatus.NOT_FOUND, message);
    }
}
