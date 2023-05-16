package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> list();
    Restaurant find(Long id);
    Restaurant save(Restaurant restaurant);
    void remove(Restaurant restaurant);
}
