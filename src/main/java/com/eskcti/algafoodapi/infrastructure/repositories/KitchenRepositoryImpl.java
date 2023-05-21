package com.eskcti.algafoodapi.infrastructure.repositories;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KitchenRepositoryImpl implements KitchenRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Kitchen> list() {
        return manager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    @Override
    public List<Kitchen> listByName(String name) {
        return manager.createQuery("from Kitchen where name like :name", Kitchen.class)
                .setParameter("name", "%"+name+"%")
                .getResultList();
    }

    @Override
    public Kitchen find(Long id) {
        Kitchen kitchen = manager.find(Kitchen.class, id);
        if (kitchen == null) throw new EmptyResultDataAccessException(1);
        return kitchen;
    }

    @Override
    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Kitchen kitchen = find(id);
        manager.remove(kitchen);
    }
}
