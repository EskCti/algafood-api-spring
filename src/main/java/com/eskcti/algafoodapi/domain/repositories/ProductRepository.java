package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Product;

import java.util.Optional;

public interface ProductRepository extends CustomJpaRepository<Product, Long>{
    Optional<Product> findByRestaurantIdAndId(Long restaurantId, Long id);
}
