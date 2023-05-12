package com.eskcti.algafoodapi.di.notification;

import com.eskcti.algafoodapi.di.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("urgent")
@Component
public class NotifierSMS implements Notifier {

    private boolean capsLock;

    @Autowired
    public NotifierSMS() {
        System.out.println("NotifierSMS");
    }

    @Override
    public void notify(Customer customer, String message) {
        if (this.capsLock) {
            message = message.toUpperCase();
        }

        System.out.printf("Notifying %s via SMS através do telefone %s: %s\n",
                customer.getName(), customer.getPhone(), message);
    }

    public void setCapsLock(boolean capsLock) {
        this.capsLock = capsLock;
    }
}
