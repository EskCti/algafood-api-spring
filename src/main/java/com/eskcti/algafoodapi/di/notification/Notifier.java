package com.eskcti.algafoodapi.di.notification;

import com.eskcti.algafoodapi.di.models.Customer;

public interface Notifier {
    void notify(Customer customer, String message);
}
