package com.eskcti.algafoodapi.api.model;

import com.eskcti.algafoodapi.domain.models.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Relation(collectionRelation = "orders")
@Getter
@Setter
public class OrderModel extends RepresentationModel<OrderModel> {
    private String code;
    private BigDecimal subtotal;
    private BigDecimal shippingFee;
    private BigDecimal valueTotal;
    private OffsetDateTime createdAt;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime cancellationDate;
    private OffsetDateTime deliveryDate;
    private OrderStatus orderStatus;
    private PaymentTypeModel paymentType;
    private RestaurantSummaryModel restaurant;

    private UserModel customer;

    private AddressModel addressDelivery;
    private List<OrderItemModel> items;
}
