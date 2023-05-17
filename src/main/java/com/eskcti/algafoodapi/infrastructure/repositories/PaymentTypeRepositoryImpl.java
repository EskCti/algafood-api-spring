package com.eskcti.algafoodapi.infrastructure.repositories;

import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.repositories.PaymentTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentTypeRepositoryImpl implements PaymentTypeRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<PaymentType> list() {
        return manager.createQuery("from PaymentType", PaymentType.class)
                .getResultList();
    }

    @Override
    public PaymentType find(Long id) {
        return manager.find(PaymentType.class, id);
    }

    @Override
    @Transactional
    public PaymentType save(PaymentType paymentType) {
        return manager.merge(paymentType);
    }

    @Override
    @Transactional
    public void remove(PaymentType paymentType) {
        paymentType = find(paymentType.getId());
        manager.remove(paymentType);
    }
}
