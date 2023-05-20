package com.eskcti.algafoodapi.infrastructure.repositories;

import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Restaurant> list() {
        return manager.createQuery("from Restaurant", Restaurant.class)
                .getResultList();
    }

    @Override
    public Restaurant find(Long id) {
        Restaurant restaurant = manager.find(Restaurant.class, id);
        if (restaurant == null) throw new EmptyResultDataAccessException(1);
        return restaurant;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Restaurant restaurant = find(id);
        manager.remove(restaurant);
    }
}
