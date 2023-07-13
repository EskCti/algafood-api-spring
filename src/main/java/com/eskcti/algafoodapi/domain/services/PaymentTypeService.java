package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.PaymentTypeNotFoundException;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.repositories.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentTypeService {
    public static final String PAYMENT_TYPE_NOT_FOUND = "Payment type not found with id %d";
    public static final String PAYMENT_TYPE_NOT_REMOVED_IN_USE = "Payment type with id %d not removed in use ";
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

    @Transactional
    public void remove(Long id) {
        try {
            paymentTypeRepository.deleteById(id);
            paymentTypeRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(PAYMENT_TYPE_NOT_REMOVED_IN_USE, id));
        }
    }
}
