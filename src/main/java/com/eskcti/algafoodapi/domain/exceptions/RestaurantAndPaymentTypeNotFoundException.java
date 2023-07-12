package com.eskcti.algafoodapi.domain.exceptions;

public class RestaurantAndPaymentTypeNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public RestaurantAndPaymentTypeNotFoundException(String message) {
        super(message);
    }

    public RestaurantAndPaymentTypeNotFoundException(Long restaurantId, Long paymentTypeId) {
        this(String.format("Restaurant with id %d Not found Payment with id %d", restaurantId, paymentTypeId));
    }
}
