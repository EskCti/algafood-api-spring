package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> list() {
        return restaurantRepository.list();
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void remove(Long id) {
        try {
            restaurantRepository.remove(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Restaurant with id %d not found", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Restaurant with id %d not removed in use ", id));
        }
    }

    public Restaurant find(Long id) {
        try {
            return restaurantRepository.find(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Restaurant with id %d not found", id));
        }
    }
}
