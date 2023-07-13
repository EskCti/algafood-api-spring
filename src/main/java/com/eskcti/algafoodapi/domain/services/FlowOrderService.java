package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.models.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FlowOrderService {
    @Autowired
    private IssuanceOrderService issuanceOrderService;

    @Transactional
    public void confirm(Long orderId) {
        Order order = issuanceOrderService.findById(orderId);

        if (!order.getOrderStatus().equals(OrderStatus.CREATED)) {
            throw new BusinessException(
                    String.format("Order status %d cannot be changed from %s to %s",
                            order.getId(),
                            order.getOrderStatus().getDescription(),
                            OrderStatus.CONFIRMED.getDescription()
                    )
            );
        }

        order.setOrderStatus(OrderStatus.CONFIRMED);
        order.setConfirmationDate(OffsetDateTime.now());
    }

    @Transactional
    public void delivery(Long orderId) {
        Order order = issuanceOrderService.findById(orderId);

        if (!order.getOrderStatus().equals(OrderStatus.CONFIRMED)) {
            throw new BusinessException(
                    String.format("Order status %d cannot be changed from %s to %s",
                            order.getId(),
                            order.getOrderStatus().getDescription(),
                            OrderStatus.DELIVERED.getDescription()
                    )
            );
        }

        order.setOrderStatus(OrderStatus.DELIVERED);
        order.setConfirmationDate(OffsetDateTime.now());
    }
    @Transactional
    public void cancel(Long orderId) {
        Order order = issuanceOrderService.findById(orderId);

        if (!order.getOrderStatus().equals(OrderStatus.CREATED)) {
            throw new BusinessException(
                    String.format("Order status %d cannot be changed from %s to %s",
                            order.getId(),
                            order.getOrderStatus().getDescription(),
                            OrderStatus.CANCELED.getDescription()
                    )
            );
        }

        order.setOrderStatus(OrderStatus.CANCELED);
        order.setConfirmationDate(OffsetDateTime.now());
    }
}
