package com.eskcti.algafoodapi.domain.events;

import com.eskcti.algafoodapi.domain.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderConfirmedEvent {
    private Order order;
}
