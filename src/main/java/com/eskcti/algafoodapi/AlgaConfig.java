package com.eskcti.algafoodapi;

import com.eskcti.algafoodapi.di.notification.NotifierEmail;
import com.eskcti.algafoodapi.di.service.ActivationCustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AlgaConfig {
    @Bean
    public NotifierEmail notifierEmail() {
        System.out.println("NotifierEmail Bean");
        NotifierEmail notifier = new NotifierEmail("smtp.algamail.com.br");
        notifier.setCapsLock(true);
        return notifier;
    }

    @Bean
    public ActivationCustomerService activationCustomerService() {
        return new ActivationCustomerService(notifierEmail());
    }
}
