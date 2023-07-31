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
import com.eskcti.algafoodapi.domain.repositories.filters.OrderFilter;
import com.eskcti.algafoodapi.domain.services.IssuanceOrderService;
import com.eskcti.algafoodapi.domain.services.OrderService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.StringUtils;
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

    @GetMapping("/filter")
    public MappingJacksonValue listFilter(@RequestParam(required = false) String fields, Pageable pageable) {
        Page<Order> ordersPage = orderService.list(pageable);
        List<OrderModel> orderModels = modelAssemblier.toCollectionModel(ordersPage.getContent());

        MappingJacksonValue ordersWrapper = new MappingJacksonValue(orderModels);

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.serializeAll());

        if (StringUtils.hasLength(fields)) {
            filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
        }

        ordersWrapper.setFilters(filterProvider);
        return ordersWrapper;
    }

    @GetMapping
    public Page<OrderSummaryModel> list(OrderFilter orderFilter, Pageable pageable) {
        Page<Order> orderPage = orderService.list(orderFilter, pageable);
        List<OrderSummaryModel> orderSummaryModels = modelSummaryAssemblier.toCollectionModel(orderPage.getContent());
        Page<OrderSummaryModel> orderSummaryModelPage = new PageImpl<>(orderSummaryModels, pageable, orderPage.getTotalElements());
        return orderSummaryModelPage;
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
