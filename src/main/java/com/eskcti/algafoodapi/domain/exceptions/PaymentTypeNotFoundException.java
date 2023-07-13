package com.eskcti.algafoodapi.domain.exceptions;

public class PaymentTypeNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1l;

    public PaymentTypeNotFoundException(String message) {
        super(message);
    }

    public PaymentTypeNotFoundException(Long paymentTypeId) {
        this(String.format("Not found State with id %d", paymentTypeId));
    }
}
