package com.eskcti.algafoodapi.di.config;

import com.eskcti.algafoodapi.di.notification.LevelNotifier;
import com.eskcti.algafoodapi.di.notification.Notifier;
import com.eskcti.algafoodapi.di.notification.TypeOfNotifier;
import com.eskcti.algafoodapi.di.service.ActivationCustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//public class ServiceConfig {
//    @Bean(initMethod = "init", destroyMethod = "destroy")
//    public ActivationCustomerService activationCustomerService(Notifier notifier) {
//        return new ActivationCustomerService(notifier);
//    }
//}
