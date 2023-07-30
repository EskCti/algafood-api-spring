package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.OrderNotFoundException;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.repositories.OrderRepository;
import com.eskcti.algafoodapi.domain.repositories.filters.OrderFilter;
import com.eskcti.algafoodapi.infrastruct.spec.OrderSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order find(String code) {
        return orderRepository.findByCode(code)
                .orElseThrow(() -> new OrderNotFoundException(code));
    }

    public List<Order> list() {
        return orderRepository.findAll();
    }

    public List<Order> list(OrderFilter orderFilter) {
        return orderRepository.findAll(OrderSpecs.withFilter(orderFilter));
    }
}
