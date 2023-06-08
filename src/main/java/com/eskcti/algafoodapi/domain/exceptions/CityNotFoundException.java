package com.eskcti.algafoodapi.domain.exceptions;

public class CityNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long cityId) {
        this(String.format("Not found City with id %d", cityId));
    }
}
