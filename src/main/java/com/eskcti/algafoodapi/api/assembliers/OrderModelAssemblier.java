package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.AlgaLinks;
import com.eskcti.algafoodapi.api.model.OrderModel;
import com.eskcti.algafoodapi.api.resources.OrderController;
import com.eskcti.algafoodapi.domain.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class OrderModelAssemblier extends RepresentationModelAssemblerSupport<Order, OrderModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;


    public OrderModelAssemblier() {
        super(OrderController.class, OrderModel.class);
    }
    public OrderModel toModel(Order order) {
        OrderModel orderModel = createModelWithId(order.getId(), order);
        modelMapper.map(order, orderModel);

        orderModel.add(algaLinks.linkToOrders());

        orderModel.getRestaurant().add(algaLinks.linkToRestaurant(order.getRestaurant().getId()));
        orderModel.getCustomer().add(algaLinks.linkToCustomer(order.getCustomer().getId()));
        orderModel.getPaymentType().add(algaLinks.linkToPaymentType(order.getPaymentType().getId()));
        orderModel.getAddressDelivery().getCity().add(algaLinks.linkToCity(order.getAddressDelivery().getCity().getId()));

        orderModel.getItems().forEach(item -> {
            item.add(algaLinks.linkToItemOrder(orderModel.getRestaurant().getId(), item.getProductId()));
        });

        return orderModel;
    }

    @Override
    public CollectionModel<OrderModel> toCollectionModel(Iterable<? extends Order> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(OrderController.class).withSelfRel());
    }

//    public List<OrderModel> toCollectionModel(List<Order> orders) {
//        return orders.stream()
//                .map(order -> toModel(order))
//                .collect(Collectors.toList());
//    }
}
