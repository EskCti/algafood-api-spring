package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.State;
import com.eskcti.algafoodapi.domain.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    @Autowired
    StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public List<State> list() {
        return stateRepository.list();
    }

    public State find(Long id) {
        try {
            return stateRepository.find(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("State not found with id %d", id));
        }
    }

    public void remove(Long id) {
        try {
            stateRepository.remove(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("State not found with id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("State with id %d not removed in use ", id));
        }
    }
}
