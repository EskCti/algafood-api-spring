package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Kitchen;

import java.util.List;

public interface KitchenRepository {
    List<Kitchen> list();
    List<Kitchen> listByName(String name);
    Kitchen find(Long id);
    Kitchen save(Kitchen kitchen);
    void remove(Long id);
}
