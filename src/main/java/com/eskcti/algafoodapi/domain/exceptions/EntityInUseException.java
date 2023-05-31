package com.eskcti.algafoodapi.domain.exceptions;

public class EntityInUseException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public EntityInUseException(String message) {
        super(message);
    }
}
