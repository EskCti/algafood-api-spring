package com.eskcti.algafoodapi.di.service;

import com.eskcti.algafoodapi.di.models.Customer;

public class CustomerActivatedEvent {
    private Customer customer;

    public CustomerActivatedEvent(Customer customer) {
        super();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
