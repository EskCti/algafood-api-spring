package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.StateNotFoundException;
import com.eskcti.algafoodapi.domain.models.State;
import com.eskcti.algafoodapi.domain.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateService {
    public static final String STATE_NOT_FOUND = "State not found with id %d";
    public static final String STATE_NOT_REMOVED_IN_USE = "State with id %d not removed in use ";
    @Autowired
    StateRepository stateRepository;

    @Transactional
    public State save(State state) {
        return stateRepository.save(state);
    }

    public List<State> list() {
        return stateRepository.findAll();
    }

    public State find(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(String.format(STATE_NOT_FOUND, id)));
        return state;
    }

    @Transactional
    public void remove(Long id) {
        try {
            find(id);
            stateRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(STATE_NOT_REMOVED_IN_USE, id));
        }
    }
}
