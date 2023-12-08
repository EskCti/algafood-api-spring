package com.eskcti.algafoodapi.core.security;

import com.eskcti.algafoodapi.domain.repositories.OrderRepository;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AlgaSecurity {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();
        return jwt.getClaim("user_id");
    }

    public boolean managerRestaurant(Long restaurantId) {
        if (restaurantId == null) {
            return false;
        }
        var existsResponsible = restaurantRepository.existsResponsible(restaurantId, getUserId());
        System.out.println("*************" + existsResponsible + "******************");
        return existsResponsible;
    }

    public boolean managerOrder(String orderCode) {
        if (orderCode == null) {
            return false;
        }
        var orderManagerBy = orderRepository.isManagerBy(orderCode, getUserId());
        System.out.println("*************" + orderManagerBy + "******************");
        return orderManagerBy;
    }
}
