package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.OrderSummaryModel;
import com.eskcti.algafoodapi.domain.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderSummaryModelAssemblier {

    @Autowired
    private ModelMapper modelMapper;
    public OrderSummaryModel toModel(Order order) {
        return modelMapper.map(order, OrderSummaryModel.class);
    }

    public List<OrderSummaryModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(order -> toModel(order))
                .collect(Collectors.toList());
    }
}
