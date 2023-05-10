package com.eskcti.algafoodapi.di.notification;

import com.eskcti.algafoodapi.di.models.Customer;
import org.springframework.stereotype.Component;

@Component
public class NotifierEmail implements Notifier {
    public NotifierEmail() {
        System.out.println("NotifierEmail");
    }

    @Override
    public void notify(Customer customer, String message) {
        System.out.printf("Notifying %s via email %s: %s\n",
                customer.getName(), customer.getEmail(), message);
    }
}
