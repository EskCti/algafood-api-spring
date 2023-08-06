package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Product;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CustomJpaRepository<Product, Long>, ProductRepositoryQueries{

    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId, @Param("product") Long productId);

    Optional<Product> findByRestaurantIdAndId(Long restaurantId, Long id);

    @Query("from Product p where p.active = true and p.restaurant = :restaurant")
    List<Product> findActivesByRestaurant(Restaurant restaurant);

    List<Product> findByRestaurant(Restaurant restaurant);
}
