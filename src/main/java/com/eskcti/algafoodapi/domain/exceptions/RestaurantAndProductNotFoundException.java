package com.eskcti.algafoodapi.domain.exceptions;

public class RestaurantAndProductNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public RestaurantAndProductNotFoundException(String message) {
        super(message);
    }

    public RestaurantAndProductNotFoundException(Long restaurantId, Long productId) {
        this(String.format("Restaurant with id %d Not found Product with id %d", restaurantId, productId));
    }
}
