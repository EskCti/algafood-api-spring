package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name,
                          BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);

    List<Restaurant> findFreeShipping(String name);
}
