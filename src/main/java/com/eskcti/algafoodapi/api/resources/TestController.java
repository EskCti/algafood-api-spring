package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.KitchenRepository;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/kitchens")
    public List<Kitchen> listByName(String name) {
        return kitchenRepository.findByName(name);
    }
    @GetMapping("/kitchens/by-name")
    public List<Kitchen> listByNameContaining(String name) {
        return kitchenRepository.findAllByNameContaining(name);
    }
    @GetMapping("/restaurants/by-shipping-fee")
    public List<Restaurant> listByShippingFee(BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal) {
        return restaurantRepository.findByShippingFeeBetween(shippingFeeInitial, shippingFeeFinal);
    }

    @GetMapping("/restaurants/by-name-and-kitchen")
    public List<Restaurant> listByNameAndKitchen(String name, Long kitchenId) {
        return restaurantRepository.findByNameContainingAndKitchenId(name, kitchenId);
    }

    @GetMapping("/restaurants/by-name-and-kitchen/custom")
    public List<Restaurant> listByNameAndKitchenCustom(String name, Long kitchenId) {
        return restaurantRepository.queryByName(name, kitchenId);
    }

    @GetMapping("/restaurants/by-name/first")
    public Restaurant listByNameFirst(String name) {
        Optional<Restaurant> restaurant = restaurantRepository.findFirstQualquerCoisaByNameContaining(name);

        if (restaurant.isPresent()) {
            return restaurant.get();
        }
        return null;
    }

    @GetMapping("/restaurants/by-name/top2")
    public List<Restaurant> listTop2ByName(String name) {
        List<Restaurant> restaurants = restaurantRepository.findTop2ByNameContaining(name);

        return restaurants;
    }

    @GetMapping("/restaurants/by-name/exists")
    public boolean existsByName(String name) {
        return restaurantRepository.existsByName(name);
    }

    @GetMapping("/restaurants/count-by-kitchen-id")
    public int countByKitchenId(Long kitchenId) {
        return restaurantRepository.countByKitchenId(kitchenId);
    }
}
