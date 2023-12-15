package com.eskcti.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.CONFLICT)
public class EntityInUseException extends BusinessException {
    private static final long serialVersionUID = 1l;

    public EntityInUseException(String message) {
        super(message);
    }
}
