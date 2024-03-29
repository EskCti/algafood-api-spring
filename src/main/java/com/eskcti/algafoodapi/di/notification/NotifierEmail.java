package com.eskcti.algafoodapi.di.notification;

import com.eskcti.algafoodapi.di.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//@Primary
//@Qualifier("normal")
//@Profile("prod")
@TypeOfNotifier(LevelNotifier.URGENT)
@Component
public class NotifierEmail implements Notifier {

    private boolean capsLock;

//    @Value("${notifier.email.host-server}")
//    private String hostServerSmtp;
//
//    @Value("${notifier.email.port-server}")
//    private Integer port;

    @Autowired
    private NotifierProperties properties;

    public NotifierEmail() {
        System.out.println("Notificador Email REAL");
    }

//    @Autowired
//    public NotifierEmail(String hostServerSmtp) {
//        this.hostServerSmtp = hostServerSmtp;
//        System.out.println("NotifierEmail");
//    }

    @Override
    public void notify(Customer customer, String message) {
        System.out.println("Host: " + properties.getHostServer());
        System.out.println(("Port: " + properties.getPortServer()));
        if (this.capsLock) {
            message = message.toUpperCase();
        }

//        System.out.printf("Notifying %s via email %s using SMTP %s: %s\n",
//                customer.getName(), customer.getEmail(), this.hostServerSmtp, message);
        System.out.printf("Notifying %s via email %s: %s\n",
                customer.getName(), customer.getEmail(), message);
    }

    public void setCapsLock(boolean capsLock) {
        this.capsLock = capsLock;
    }
}
