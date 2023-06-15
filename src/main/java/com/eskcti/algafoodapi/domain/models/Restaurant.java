package com.eskcti.algafoodapi.domain.models;

import com.eskcti.algafoodapi.core.validation.Groups;
import com.eskcti.algafoodapi.core.validation.Multiple;
import com.eskcti.algafoodapi.core.validation.ShippingFee;
import com.eskcti.algafoodapi.core.validation.ValueZeroIncludesDescription;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ValueZeroIncludesDescription(
        valueField = "shippingFee",
        descriptionField = "name",
        descriptionRequired = "Free Shipping"
)
@Entity
@Table(name = "tab_restaurants")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

//    @PositiveOrZero
    @ShippingFee
    @Multiple(number = 5)
    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime updatedAt;

    @Valid
    @NotNull
    @ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(name = "tab_restaurants_payments_types",
        joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "payment_type_id")
    )
    private List<PaymentType> paymentTypes = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();
}
