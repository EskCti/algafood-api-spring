package com.eskcti.algafoodapi.domain.models.mixins;

import com.eskcti.algafoodapi.domain.models.Address;
import com.eskcti.algafoodapi.domain.models.Kitchen;
import com.eskcti.algafoodapi.domain.models.PaymentType;
import com.eskcti.algafoodapi.domain.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantMixin {
    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonIgnoreProperties(value = {"name"}, allowGetters = true)
    private Kitchen kitchen;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private List<PaymentType> paymentTypes = new ArrayList<>();

    @JsonIgnore
    private List<Product> products = new ArrayList<>();
}
