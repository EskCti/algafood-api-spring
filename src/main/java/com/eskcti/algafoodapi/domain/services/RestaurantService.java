package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.CityNotFoundException;
import com.eskcti.algafoodapi.domain.exceptions.EntityInUseException;
import com.eskcti.algafoodapi.domain.exceptions.KitchenNotFoundException;
import com.eskcti.algafoodapi.domain.exceptions.RestaurantNotFoundException;
import com.eskcti.algafoodapi.domain.models.*;
import com.eskcti.algafoodapi.domain.repositories.CityRepository;
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
    private CityRepository cityRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private PaymentTypeService paymentTypeService;

    @Autowired
    private ProductService productService;

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
        if (restaurant.getKitchen() == null) {
            throw new KitchenNotFoundException("Not found kitchen in request");
        }
        Long cityId = restaurant.getAddress().getCity().getId();
        if (cityId == null) {
            throw new CityNotFoundException("Not found city without id");
        }
        City city = cityRepository.findById(cityId)
                .orElseThrow(
                        () -> new CityNotFoundException(cityId)
                );
        restaurant.setKitchen(kitchen);
        restaurant.getAddress().setCity(city);
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

    @Transactional
    public void activate(Long id) {
       Restaurant restaurant = find(id);
       restaurant.activate();
    }

    @Transactional
    public void deactivate(Long id) {
        Restaurant restaurant = find(id);
        restaurant.deactivate();
    }
    @Transactional
    public void disassociatePaymentType(Long restaurantId, Long paymentTypeId) {
        Restaurant restaurant = find(restaurantId);
        PaymentType paymentType = paymentTypeService.find(paymentTypeId);

        restaurant.disassociatePaymentType(paymentType);
    }
    @Transactional
    public void associatePaymentType(Long restaurantId, Long paymentTypeId) {
        Restaurant restaurant = find(restaurantId);
        PaymentType paymentType = paymentTypeService.find(paymentTypeId);

        restaurant.associatePaymentType(paymentType);
    }
    public Product findByRestaurantIdAndProductId(Long restaurantId, Long productId) {
        Restaurant restaurant = find(restaurantId);
        Product product = productService.find(productId);

        return productService.findByRestaurantIdAndId(restaurantId, productId);
    }
    public Restaurant find(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        return restaurant;
    }
}
