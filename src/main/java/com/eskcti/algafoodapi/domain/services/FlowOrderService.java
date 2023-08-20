package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlowOrderService {
    @Autowired
    private IssuanceOrderService issuanceOrderService;
    @Autowired
    private SendEmailService sendEmailService;

    @Transactional
    public void confirm(String orderCode) {
        Order order = issuanceOrderService.findByCode(orderCode);
        order.confirm();

        var message = SendEmailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Pedido confirmado")
                .recipient(order.getCustomer().getEmail())
                .variable("order", order)
                .body("confirmed-order.htm")
                .build();


        sendEmailService.send(message);
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
    }
}
