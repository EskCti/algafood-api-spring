package com.eskcti.algafoodapi.infrastructure.repositories;

import com.eskcti.algafoodapi.domain.models.State;
import com.eskcti.algafoodapi.domain.repositories.StateRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<State> list() {
        return manager.createQuery("from State", State.class)
                .getResultList();
    }

    @Override
    public State find(Long id) {
        return manager.find(State.class, id);
    }

    @Override
    @Transactional
    public State save(State state) {
        return manager.merge(state);
    }

    @Override
    @Transactional
    public void remove(State state) {
        state = find(state.getId());
        manager.remove(state);
    }
}
