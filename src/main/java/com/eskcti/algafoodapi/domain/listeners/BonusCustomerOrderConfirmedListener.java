package com.eskcti.algafoodapi.domain.listeners;

import com.eskcti.algafoodapi.domain.events.OrderConfirmedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class BonusCustomerOrderConfirmedListener {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void whenConfirmingOrder(OrderConfirmedEvent event) {
        log.info("Calculando pontos para o cliente " + event.getOrder().getCustomer().getName());
    }
}
