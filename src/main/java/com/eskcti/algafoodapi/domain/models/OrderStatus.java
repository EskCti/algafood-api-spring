package com.eskcti.algafoodapi.domain.models;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {
    CREATED("Created"),
    CONFIRMED("Confirmed", CREATED),
    DELIVERED("Delivered", CONFIRMED),
    CANCELED("Canceled", CREATED);

    private String description;
    private List<OrderStatus> statusOlds;

    OrderStatus(String description, OrderStatus... statusOlds) {
        this.description = description;
        this.statusOlds = Arrays.asList(statusOlds);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean cannotChangeTo(OrderStatus newStatus) {
        return !newStatus.statusOlds.contains(this);
    }

    public boolean canChangeTo(OrderStatus newStatus) {
        return !cannotChangeTo(newStatus);
    }
}
