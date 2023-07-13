package com.eskcti.algafoodapi.api.resources;

import com.eskcti.algafoodapi.api.assembliers.OrderInputDisassembler;
import com.eskcti.algafoodapi.api.assembliers.OrderModelAssemblier;
import com.eskcti.algafoodapi.api.assembliers.OrderSummaryModelAssemblier;
import com.eskcti.algafoodapi.api.model.OrderModel;
import com.eskcti.algafoodapi.api.model.OrderSummaryModel;
import com.eskcti.algafoodapi.api.model.input.OrderInput;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.models.User;
import com.eskcti.algafoodapi.domain.services.IssuanceOrderService;
import com.eskcti.algafoodapi.domain.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private OrderInputDisassembler inputDisassembler;

    @Autowired
    private IssuanceOrderService issuanceOrderService;

    @GetMapping("/{orderCode}")
    public OrderModel find(@PathVariable String orderCode) {
        return modelAssemblier.toModel(orderService.find(orderCode));
    }

    @GetMapping
    public List<OrderSummaryModel> list() {
        return modelSummaryAssemblier.toCollectionModel(orderService.list());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderModel add(@Valid @RequestBody OrderInput orderInput) {
        try {
            Order newOrder = inputDisassembler.toDomainObject(orderInput);
            newOrder.setCustomer(new User());
            newOrder.getCustomer().setId(1L);

            newOrder = issuanceOrderService.issuance(newOrder);

            return modelAssemblier.toModel(newOrder);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
