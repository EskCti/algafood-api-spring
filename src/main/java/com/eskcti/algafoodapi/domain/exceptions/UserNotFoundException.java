package com.eskcti.algafoodapi.domain.exceptions;

public class UserNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        this(String.format("Not found User with id %d", userId));
    }
}
