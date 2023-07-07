package com.eskcti.algafoodapi.api.model;

import com.eskcti.algafoodapi.domain.models.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderModel {
    private Long id;
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
