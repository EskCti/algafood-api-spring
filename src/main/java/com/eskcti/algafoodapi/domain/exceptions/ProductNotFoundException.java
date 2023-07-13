package com.eskcti.algafoodapi.domain.exceptions;

public class ProductNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long productId) {
        this(String.format("Not found Product with id %d", productId));
    }
}
