package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlowOrderService {
    @Autowired
    private IssuanceOrderService issuanceOrderService;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void confirm(String orderCode) {
        Order order = issuanceOrderService.findByCode(orderCode);
        order.confirm();

        orderRepository.save(order);
    }

    @Transactional
    public void delivery(String orderCode) {
        Order order = issuanceOrderService.findByCode(orderCode);
        order.delivery();
    }
    @Transactional
    public void cancel(String orderCode) {
        Order order = issuanceOrderService.findByCode(orderCode);
        order.cancel();

        orderRepository.save(order);
    }
}
