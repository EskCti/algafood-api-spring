package com.eskcti.algafoodapi.domain.exceptions;

public class RestaurantNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long restaurantId) {
        this(String.format("Not found Restaurant with id %d", restaurantId));
    }
}
