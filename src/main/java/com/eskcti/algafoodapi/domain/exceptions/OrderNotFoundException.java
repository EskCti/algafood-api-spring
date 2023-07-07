package com.eskcti.algafoodapi.domain.exceptions;

public class OrderNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(Long orderId) {
        this(String.format("Not found Order with id %d", orderId));
    }
}
