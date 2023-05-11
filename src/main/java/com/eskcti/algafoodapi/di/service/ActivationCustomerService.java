package com.eskcti.algafoodapi.di.service;

import com.eskcti.algafoodapi.di.models.Customer;
import com.eskcti.algafoodapi.di.notification.Notifier;
import org.springframework.stereotype.Component;

public class ActivationCustomerService {
    private Notifier notifier;

    public ActivationCustomerService(Notifier notifier) {
        this.notifier = notifier;

        System.out.println("ActivationCustomerService");
    }

    public void activate(Customer customer) {
        customer.activate();

        notifier.notify(customer, "Your registration in the system is active.");
    }
}
