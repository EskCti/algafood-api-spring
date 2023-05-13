package com.eskcti.algafoodapi.listener;

import com.eskcti.algafoodapi.di.notification.LevelNotifier;
import com.eskcti.algafoodapi.di.notification.Notifier;
import com.eskcti.algafoodapi.di.notification.TypeOfNotifier;
import com.eskcti.algafoodapi.di.service.CustomerActivatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    @TypeOfNotifier(LevelNotifier.URGENT)
    @Autowired
    private Notifier notifier;
    @EventListener
    public void customerActivedListener(CustomerActivatedEvent event) {
        System.out.println("Customer " + event.getCustomer().getName() + " is now active");
        notifier.notify(event.getCustomer(), "Your registration in the system is active");
    }
}
