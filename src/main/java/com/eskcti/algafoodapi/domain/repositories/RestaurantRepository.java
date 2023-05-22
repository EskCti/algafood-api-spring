package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository  extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByShippingFeeBetween(BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);

    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);
}
