package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.KitchenNotFoundException;
import com.eskcti.algafoodapi.domain.exceptions.RestaurantNotFoundException;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    public static final String RESTAURANT_REMOVED_IN_USE = "Restaurant with id %d not removed in use ";
    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getKitchen() == null) {
            throw new KitchenNotFoundException("Not found kitchen in request");
        }
        Long kitchenId = restaurant.getKitchen().getId();
        if (kitchenId == null) {
            throw new KitchenNotFoundException("Not found kitchen without id");
        }
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(
                        () -> new KitchenNotFoundException(kitchenId)
                );
        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void remove(Long id) {
        try {
            find(id);
            restaurantRepository.deleteById(id);
            restaurantRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(RESTAURANT_REMOVED_IN_USE, id));
        }
    }

    public Restaurant find(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        return restaurant;
    }
}
