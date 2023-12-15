package com.eskcti.algafoodapi.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table(name = "tab_orders_items")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "price_unit", nullable = false)
    private BigDecimal priceUnit;

    @Column(name = "price_total", nullable = false)
    private BigDecimal priceTotal;

    private String observation;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    public void calculatePriceTotal() {
        BigDecimal priceUnit = this.getPriceUnit();
        Integer quantity = this.getQuantity();

        if (priceUnit == null) {
            priceUnit = BigDecimal.ZERO;
        }

        if (quantity == null) {
            quantity = 0;
        }
        this.setPriceTotal(priceUnit.multiply(new BigDecimal(quantity)));
    }
}
