package com.eskcti.algafoodapi.di.notification;

import com.eskcti.algafoodapi.di.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//@Primary
//@Qualifier("normal")
@Profile("dev")
@TypeOfNotifier(LevelNotifier.NORMAL)
@Component
public class NotifierEmailMock implements Notifier {

    private boolean capsLock;
    private String hostServerSmtp;

    public NotifierEmailMock() {
        System.out.println("NotificadorEmail MOCK");
    }
//    @Autowired
//    public NotifierEmailMock(String hostServerSmtp) {
//        this.hostServerSmtp = hostServerSmtp;
//        System.out.println("NotifierEmailMock");
//    }

    @Override
    public void notify(Customer customer, String message) {
        if (this.capsLock) {
            message = message.toUpperCase();
        }

        System.out.printf("MOCK: Notification would be sent to %s via email %s: %s\n",
                customer.getName(), customer.getEmail(), message);
    }

    public void setCapsLock(boolean capsLock) {
        this.capsLock = capsLock;
    }
}
