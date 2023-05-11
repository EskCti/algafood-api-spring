package com.eskcti.algafoodapi.di.service;

import com.eskcti.algafoodapi.di.models.Customer;
import com.eskcti.algafoodapi.di.notification.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivationCustomerService {
    @Autowired(required = false)
    private Notifier notifier;

//    @Autowired
//    public ActivationCustomerService(Notifier notifier) {
//        this.notifier = notifier;
//
//        System.out.println("ActivationCustomerService");
//    }

    public ActivationCustomerService(String qualquer) {

    }

    public void activate(Customer customer) {
        customer.activate();

        if (notifier != null) {
            notifier.notify(customer, "Your registration in the system is active.");
        } else {
            System.out.println("Not notifier exists, but customer has been activated");
        }
    }
}
