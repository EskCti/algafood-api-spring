package com.eskcti.algafoodapi.di.notification;

import com.eskcti.algafoodapi.di.models.Client;
import org.springframework.stereotype.Component;

@Component
public class NotifierEmail {
    public void notifier(Client client, String message) {
        System.out.printf("Notifying %s via email %s: %s\n",
                client.getName(), client.getEmail(), message);
    }
}
