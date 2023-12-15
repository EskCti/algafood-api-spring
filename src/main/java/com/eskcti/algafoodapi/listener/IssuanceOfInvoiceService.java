package com.eskcti.algafoodapi.listener;

import com.eskcti.algafoodapi.di.service.CustomerActivatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class IssuanceOfInvoiceService {
    @EventListener
    public void customerActivedListener(CustomerActivatedEvent event) {
        System.out.println("Issuing invoice to customer " + event.getCustomer().getName());
    }
}
