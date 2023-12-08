package com.eskcti.algafoodapi.api.v1.resources;

import com.eskcti.algafoodapi.api.v1.assembliers.OrderInputDisassembler;
import com.eskcti.algafoodapi.api.v1.assembliers.OrderModelAssemblier;
import com.eskcti.algafoodapi.api.v1.assembliers.OrderSummaryModelAssemblier;
import com.eskcti.algafoodapi.api.v1.model.OrderModel;
import com.eskcti.algafoodapi.api.v1.model.OrderSummaryModel;
import com.eskcti.algafoodapi.api.v1.model.input.OrderInput;
import com.eskcti.algafoodapi.api.v1.openapi.OrderControllerOpenApi;
import com.eskcti.algafoodapi.core.data.PageWrapper;
import com.eskcti.algafoodapi.core.data.PageableTranslate;
import com.eskcti.algafoodapi.core.security.AlgaSecurity;
import com.eskcti.algafoodapi.core.security.CheckSecutiry;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.EntityNotFoundException;
import com.eskcti.algafoodapi.domain.filter.OrderFilter;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.models.User;
import com.eskcti.algafoodapi.domain.services.IssuanceOrderService;
import com.eskcti.algafoodapi.domain.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/orders")
public class OrderController implements OrderControllerOpenApi {

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

    @Autowired
    private PagedResourcesAssembler<Order> pagedResourcesAssembler;

    @Autowired
    private AlgaSecurity algaSecurity;

    @CheckSecutiry.Orders.CanFind
    @GetMapping("/{orderCode}")
    public OrderModel find(@PathVariable String orderCode) {
        return modelAssemblier.toModel(orderService.find(orderCode));
    }

//    @GetMapping("/search/filter")
//    public MappingJacksonValue listFilter(@RequestParam(required = false) String fields, Pageable pageable) {
//        Page<Order> ordersPage = orderService.list(pageable);
//        CollectionModel<OrderModel> orderModels = modelAssemblier.toCollectionModel(ordersPage.getContent());
//
//        MappingJacksonValue ordersWrapper = new MappingJacksonValue(orderModels);
//
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//
//        if (StringUtils.hasLength(fields)) {
//            filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
//        } else {
//            filterProvider.addFilter("orderFilter", SimpleBeanPropertyFilter.serializeAll());
//        }
//
//        ordersWrapper.setFilters(filterProvider);
//        return ordersWrapper;
//    }

    @CheckSecutiry.Orders.CanList
    @GetMapping
    public PagedModel<OrderSummaryModel> list(OrderFilter orderFilter, Pageable pageable) {
        Pageable pageableTranslate = translatePageable(pageable);
        Page<Order> orderPage = orderService.list(orderFilter, pageableTranslate);

        orderPage = new PageWrapper<>(orderPage, pageable);

        return pagedResourcesAssembler.toModel(orderPage, modelSummaryAssemblier);
    }

    @CheckSecutiry.Orders.CanCreate
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderModel add(@Valid @RequestBody OrderInput orderInput) {
        try {
            Order newOrder = inputDisassembler.toDomainObject(orderInput);
            newOrder.setCustomer(new User());
            newOrder.getCustomer().setId(algaSecurity.getUserId());

            newOrder = issuanceOrderService.issuance(newOrder);

            return modelAssemblier.toModel(newOrder);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    private Pageable translatePageable(Pageable pageable) {
        var mapping = Map.of(
                "code", "code",
                "restaurant.id", "restaurant.id",
                "nameRestaurant", "restaurant.name",
                "nameCustomer", "customer.name",
                "subtotal", "subtotal",
                "shippingFee", "shippingFee",
                "valueTotal", "valueTotal"
        );
        return PageableTranslate.translate(pageable, mapping);
    }
}
