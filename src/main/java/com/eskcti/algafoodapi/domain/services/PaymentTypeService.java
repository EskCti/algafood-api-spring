package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.PaymentTypeNotFoundException;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.repositories.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentTypeService {
    public static final String PAYMENT_TYPE_NOT_FOUND = "Payment type not found with id %d";
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Transactional
    public PaymentType save(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }

    public List<PaymentType> list() {
        return paymentTypeRepository.findAll();
    }

    public PaymentType find(Long id) {
        PaymentType paymentType = paymentTypeRepository
                .findById(id)
                .orElseThrow(() -> new PaymentTypeNotFoundException(String.format(PAYMENT_TYPE_NOT_FOUND, id)));
        return paymentType;
    }
}
