package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CustomJpaRepository<Order, Long>,
        JpaSpecificationExecutor<Order>
{
    Optional<Order> findByCode(String code);
    @Query("from Order o join fetch o.customer c join fetch o.restaurant r join fetch r.kitchen")
    List<Order> findAll();
}
