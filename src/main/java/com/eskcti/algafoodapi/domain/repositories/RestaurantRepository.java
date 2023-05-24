package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository  extends
        JpaRepository<Restaurant, Long>,
        RestaurantRepositoryQueries,
        JpaSpecificationExecutor<Restaurant>
{
    List<Restaurant> findByShippingFeeBetween(BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal);

//    @Query("from Restaurant where name like %:name% and kitchen.id = :id")
    List<Restaurant> queryByName(String name, @Param("id") Long kitchenId);

    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);

    Optional<Restaurant> findFirstQualquerCoisaByNameContaining(String name);

    List<Restaurant> findTop2ByNameContaining(String name);

    boolean existsByName(String name);

    int countByKitchenId(Long kitchenId);
}
