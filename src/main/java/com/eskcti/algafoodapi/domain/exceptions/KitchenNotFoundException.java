package com.eskcti.algafoodapi.domain.exceptions;

public class KitchenNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long kitchenId) {
        this(String.format("Not found Kitchen with id %d", kitchenId));
    }
}
