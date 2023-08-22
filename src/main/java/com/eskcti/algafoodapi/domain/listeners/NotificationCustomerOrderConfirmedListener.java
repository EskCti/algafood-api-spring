package com.eskcti.algafoodapi.domain.listeners;

import com.eskcti.algafoodapi.domain.events.OrderConfirmedEvent;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationCustomerOrderConfirmedListener {
    @Autowired
    private SendEmailService sendEmailService;
    @EventListener
    public void whenConfirmingOrder(OrderConfirmedEvent event) {
        Order order = event.getOrder();

        var message = SendEmailService.Message.builder()
                .subject(order.getRestaurant().getName() + " - Pedido confirmado")
                .recipient(order.getCustomer().getEmail())
                .variable("order", order)
                .body("confirmed-order.htm")
                .build();


        sendEmailService.send(message);
    }
}
