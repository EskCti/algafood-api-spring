package com.eskcti.algafoodapi.di.service;

import com.eskcti.algafoodapi.di.models.Customer;
import com.eskcti.algafoodapi.di.notification.LevelNotifier;
import com.eskcti.algafoodapi.di.notification.Notifier;
import com.eskcti.algafoodapi.di.notification.TypeOfNotifier;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class ActivationCustomerService {

//    @Qualifier("urgent")
//    @TypeOfNotifier(LevelNotifier.URGENT)
//    @Autowired(required = false)
//    private List<Notifier> notifiers;
    private Notifier notifier;

    @Autowired
    public ActivationCustomerService(Notifier notifier) {
        this.notifier = notifier;

        System.out.println("ActivationCustomerService");
    }
//
//    @Autowired
//    public ActivationCustomerService() {
//    }

    public ActivationCustomerService(String qualquer) {

    }

//    @PostConstruct
    public void init() {
        System.out.println("INIT");
    }

//    @PreDestroy
    public void destroy() {
        System.out.println("DESTROY");
    }

    public void activate(Customer customer) {
        customer.activate();

//        for (Notifier notifier : notifiers) {
            notifier.notify(customer, "Your registration in the system is active.");
//        }
//        if (notifier != null) {
//            notifier.notify(customer, "Your registration in the system is active.");
//        } else {
//            System.out.println("Not notifier exists, but customer has been activated");
//        }
    }
}
