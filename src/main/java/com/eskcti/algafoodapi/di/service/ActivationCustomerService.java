package com.eskcti.algafoodapi.di.service;

import com.eskcti.algafoodapi.di.models.Customer;
import com.eskcti.algafoodapi.di.notification.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivationCustomerService {
    @Autowired
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

        notifier.notify(customer, "Your registration in the system is active.");
    }
}
