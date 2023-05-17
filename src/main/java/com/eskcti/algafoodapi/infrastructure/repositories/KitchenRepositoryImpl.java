package com.eskcti.algafoodapi.infrastructure.repositories;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Kitchen> list() {
        return manager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    @Override
    public Kitchen find(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    @Override
    @Transactional
    public void remove(Kitchen kitchen) {
        kitchen = find(kitchen.getId());
        manager.remove(kitchen);
    }
}
