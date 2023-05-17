package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.PaymentType;

import java.util.List;

public interface PaymentTypeRepository {
    List<PaymentType> list();
    PaymentType find(Long id);
    PaymentType save(PaymentType paymentType);
    void remove(PaymentType paymentType);
}
