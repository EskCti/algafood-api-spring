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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotNull
    private Boolean active = Boolean.TRUE;

    @NotNull
    private Boolean open = Boolean.FALSE;

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
    private Set<PaymentType> paymentTypes = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tab_restaurants_responsible",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> responsible = new HashSet<>();


    public void activate() {
        setActive(true);
    }

    public void deactivate() {
        setActive(false);
    }

    public void opening() {
        setOpen(true);
    }

    public void closing() {
        setOpen(false);
    }

    public boolean disassociatePaymentType(PaymentType paymentType) {
        return this.getPaymentTypes().remove(paymentType);
    }

    public boolean associatePaymentType(PaymentType paymentType) {
        return this.getPaymentTypes().add(paymentType);
    }

    public Boolean associateResponsible(User responsible) {
        return this.getResponsible().add(responsible);
    }

    public Boolean disassociateResponsible(User responsible) {
        return this.getResponsible().remove(responsible);
    }
}
