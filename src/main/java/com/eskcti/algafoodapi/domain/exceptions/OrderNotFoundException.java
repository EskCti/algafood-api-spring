package com.eskcti.algafoodapi.domain.exceptions;

public class OrderNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public OrderNotFoundException(String orderCode) {
        super(String.format("Not found Order with code %s", orderCode));
    }
}
