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
}
