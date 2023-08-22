package com.eskcti.algafoodapi.domain.listeners;

import com.eskcti.algafoodapi.domain.events.OrderCanceledEvent;
import com.eskcti.algafoodapi.domain.events.OrderConfirmedEvent;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificationCustomerOrderCanceledListener {
    @Autowired
    private SendEmailService sendEmailService;
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void whenConfirmingOrder(OrderCanceledEvent event) {
        Order order = event.getOrder();

        var message = SendEmailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Pedido cancelado")
                .recipient(order.getCustomer().getEmail())
                .variable("order", order)
                .body("canceled-order.htm")
                .build();


        sendEmailService.send(message);
    }
}
