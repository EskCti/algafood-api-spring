package com.eskcti.algafoodapi.jpa;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CadastroCozinha {
    @PersistenceContext
    private EntityManager manager;

    public List<Kitchen> listar() {
        return manager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    public Kitchen find(Long id) {
        return manager.find(Kitchen.class, id);
    }
}
