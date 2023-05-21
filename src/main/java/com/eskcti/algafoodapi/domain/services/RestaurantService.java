package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> list() {
        return restaurantRepository.list();
    }

    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getKitchen() == null) {
            throw new EntityNotFoundException("Not found kitchen in request");
        }
        Long kitchenId = restaurant.getKitchen().getId();
        if (kitchenId == null) {
            throw new EntityNotFoundException("Not found kitchen without id");
        }
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("Not found kitchen with id %d", kitchenId))
                );
        restaurant.setKitchen(kitchen);
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
