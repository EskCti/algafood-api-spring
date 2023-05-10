package com.eskcti.algafoodapi.di.service;

import com.eskcti.algafoodapi.di.models.Customer;
import com.eskcti.algafoodapi.di.notification.NotifierEmail;
import org.springframework.stereotype.Component;

@Component
public class ActivationCustomerService {
    private NotifierEmail notifier;

    public void activate(Customer customer) {
        customer.activate();

        notifier.notify(customer, "Your registration in the system is active.");
    }
}
