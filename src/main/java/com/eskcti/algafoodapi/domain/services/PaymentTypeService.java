package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.repositories.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentTypeService {
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Transactional
    public PaymentType save(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }

    public List<PaymentType> list() {
        return paymentTypeRepository.findAll();
    }


}
