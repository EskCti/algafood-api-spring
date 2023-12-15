package com.eskcti.algafoodapi.domain.models;

import com.eskcti.algafoodapi.domain.events.OrderCanceledEvent;
import com.eskcti.algafoodapi.domain.events.OrderConfirmedEvent;
import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tab_orders")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Order extends AbstractAggregateRoot<Order> {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    @Column(name = "value_total", nullable = false)
    private BigDecimal valueTotal;

    @Column(name = "created_at", nullable = false, columnDefinition = "datetime")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "confirmation_date", columnDefinition = "datetime")
    private OffsetDateTime confirmationDate;

    @Column(name = "cancellation_date")
    private OffsetDateTime cancellationDate;

    @Column(name = "delivery_date")
    private OffsetDateTime deliveryDate;

    @Column(name = "order_status", length = 15)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id", nullable = false)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @Embedded
    private Address addressDelivery;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    public void calculateValueTotal() {
        getItems().forEach(OrderItem::calculatePriceTotal);

        this.subtotal = getItems().stream()
                .map(item -> item.getPriceTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valueTotal = this.subtotal.add(this.shippingFee);
    }

    public void confirm() {
        setOrderStatus(OrderStatus.CONFIRMED);
        setConfirmationDate(OffsetDateTime.now());
        calculateValueTotal();
        registerEvent(new OrderConfirmedEvent(this));
    }

    public boolean canBeConfirmed() {
        return getOrderStatus().canChangeTo(OrderStatus.CONFIRMED);
    }

    public void delivery() {
        setOrderStatus(OrderStatus.DELIVERED);
        setDeliveryDate(OffsetDateTime.now());
        calculateValueTotal();
    }

    public boolean canBeDelivered() {
        return getOrderStatus().canChangeTo(OrderStatus.DELIVERED);
    }

    public void cancel() {
        setOrderStatus(OrderStatus.CANCELED);
        setCancellationDate(OffsetDateTime.now());
        calculateValueTotal();
        registerEvent(new OrderCanceledEvent(this));
    }

    public boolean canBeCanceled() {
        return getOrderStatus().canChangeTo(OrderStatus.CANCELED);
    }

    private void setStatus(OrderStatus newStatus) {
        if (getOrderStatus().cannotChangeTo(newStatus)) {
            throw new BusinessException(
                    String.format("Order status %s cannot be changed from %s to %s",
                            getCode(),
                            getOrderStatus().getDescription(),
                            OrderStatus.CANCELED.getDescription()
                    )
            );
        }

        this.orderStatus = newStatus;
    }

    @PrePersist
    private void generateCode() {
        setCode(UUID.randomUUID().toString());
    }
}
