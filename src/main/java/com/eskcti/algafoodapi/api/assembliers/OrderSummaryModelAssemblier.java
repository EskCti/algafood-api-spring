package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.OrderSummaryModel;
import com.eskcti.algafoodapi.api.resources.OrderController;
import com.eskcti.algafoodapi.api.resources.RestaurantController;
import com.eskcti.algafoodapi.domain.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderSummaryModelAssemblier extends RepresentationModelAssemblerSupport<Order, OrderSummaryModel> {

    @Autowired
    private ModelMapper modelMapper;

    public OrderSummaryModelAssemblier() {
        super(OrderController.class, OrderSummaryModel.class);
    }
    public OrderSummaryModel toModel(Order order) {
        OrderSummaryModel orderModel = createModelWithId(order.getCode(), order);
        modelMapper.map(order, orderModel);

        orderModel.add(linkTo(OrderController.class).withRel("pedidos"));

        orderModel.getRestaurant().add(linkTo(methodOn(RestaurantController.class)
                .find(order.getRestaurant().getId())).withSelfRel());

        orderModel.getCustomer().add(linkTo(methodOn(RestaurantController.class)
                .find(order.getCustomer().getId())).withSelfRel());

        return orderModel;
    }

    @Override
    public CollectionModel<OrderSummaryModel> toCollectionModel(Iterable<? extends Order> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(OrderController.class).withSelfRel());
    }

    //    public List<OrderSummaryModel> toCollectionModel(List<Order> orders) {
//        return orders.stream()
//                .map(order -> toModel(order))
//                .collect(Collectors.toList());
//    }
}
