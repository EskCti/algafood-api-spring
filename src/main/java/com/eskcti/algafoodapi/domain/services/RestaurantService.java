package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    public static final String RESTAURANT_NOT_FOUND = "Restaurant with id %d not found";
    public static final String RESTAURANT_REMOVED_IN_USE = "Restaurant with id %d not removed in use ";
    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> list() {
        return restaurantRepository.findAll();
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
            find(id);
            restaurantRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(RESTAURANT_REMOVED_IN_USE, id));
        }
    }

    public Restaurant find(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(RESTAURANT_NOT_FOUND, id)));
        return restaurant;
    }
}
