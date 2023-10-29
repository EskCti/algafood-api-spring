package com.eskcti.algafoodapi.api.model;

import com.eskcti.algafoodapi.domain.models.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String code;

    @Schema(example = "298.90")
    private BigDecimal subtotal;

    @Schema(example = "10.00")
    private BigDecimal shippingFee;

    @Schema(example = "308.90")
    private BigDecimal valueTotal;

    @Schema(example = "2022-12-01T20:34:04Z")
    private OffsetDateTime createdAt;

    @Schema(example = "2022-12-01T20:35:10Z")
    private OffsetDateTime confirmationDate;

    @Schema(example = "2022-12-01T20:55:30Z")
    private OffsetDateTime cancellationDate;

    @Schema(example = "2022-12-01T20:35:00Z")
    private OffsetDateTime deliveryDate;
    private OrderStatus orderStatus;
    private PaymentTypeModel paymentType;
    private RestaurantSummaryModel restaurant;

    private UserModel customer;

    private AddressModel addressDelivery;
    private List<OrderItemModel> items;
}
