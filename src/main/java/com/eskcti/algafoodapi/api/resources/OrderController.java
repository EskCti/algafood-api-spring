package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.OrderModelAssemblier;
import com.eskcti.algafoodapi.api.assembliers.OrderSummaryModelAssemblier;
import com.eskcti.algafoodapi.api.model.OrderModel;
import com.eskcti.algafoodapi.api.model.OrderSummaryModel;
import com.eskcti.algafoodapi.domain.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderModelAssemblier modelAssemblier;
    @Autowired
    private OrderSummaryModelAssemblier modelSummaryAssemblier;

    @GetMapping("/{id}")
    public OrderModel find(@PathVariable Long id) {
        return modelAssemblier.toModel(orderService.find(id));
    }

    @GetMapping
    public List<OrderSummaryModel> list() {
        return modelSummaryAssemblier.toCollectionModel(orderService.list());
    }
}
