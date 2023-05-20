package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.State;

import java.util.List;

public interface StateRepository {
    List<State> list();
    State find(Long id);
    State save(State state);
    void remove(Long id);
}
