package com.eskcti.algafoodapi.di.service;

import com.eskcti.algafoodapi.di.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ActivationCustomerService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void activate(Customer customer) {
        customer.activate();

        eventPublisher.publishEvent(new CustomerActivatedEvent(customer));
    }
}
