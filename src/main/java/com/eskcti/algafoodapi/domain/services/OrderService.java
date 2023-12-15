package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.OrderNotFoundException;
import com.eskcti.algafoodapi.domain.filter.OrderFilter;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.repositories.OrderRepository;
import com.eskcti.algafoodapi.infrastruct.spec.OrderSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order find(String code) {
        return orderRepository.findByCode(code)
                .orElseThrow(() -> new OrderNotFoundException(code));
    }

    public Page<Order> list(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Page<Order> list(OrderFilter orderFilter, Pageable pageable) {
        return orderRepository.findAll(OrderSpecs.withFilter(orderFilter), pageable);
    }
}
