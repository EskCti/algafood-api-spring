package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends CustomJpaRepository<Order, Long> {
    @Query("from Order o join fetch o.customer c join fetch o.restaurant r join fetch r.kitchen")
    List<Order> findAlll();
}
