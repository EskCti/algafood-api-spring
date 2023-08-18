package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.models.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Collections;

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
                .addressee(order.getCustomer().getEmail())
                .body("The Order of code <strong>" + order.getCode() + "</strong> foi confirmado!")
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
