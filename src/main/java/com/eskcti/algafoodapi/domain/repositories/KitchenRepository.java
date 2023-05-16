package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Kitchen;

import java.util.List;

public interface KitchenRepository {
    List<Kitchen> list();
    Kitchen find(Long id);
    Kitchen save(Kitchen kitchen);
    void remove(Kitchen kitchen);
}
