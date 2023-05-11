package com.eskcti.algafoodapi.di.config;

import com.eskcti.algafoodapi.di.notification.NotifierEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class NotificationConfig {
    @Bean
    public NotifierEmail notifierEmail() {
        System.out.println("NotifierEmail Bean");
        NotifierEmail notifier = new NotifierEmail("smtp.algamail.com.br");
        notifier.setCapsLock(true);
        return notifier;
    }
}
