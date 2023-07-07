package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.CityModel;
import com.eskcti.algafoodapi.api.model.OrderModel;
import com.eskcti.algafoodapi.domain.models.City;
import com.eskcti.algafoodapi.domain.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderModelAssemblier {

    @Autowired
    private ModelMapper modelMapper;
    public OrderModel toModel(Order order) {
        return modelMapper.map(order, OrderModel.class);
    }

    public List<OrderModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(order -> toModel(order))
                .collect(Collectors.toList());
    }
}
