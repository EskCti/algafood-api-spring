package com.eskcti.algafoodapi.infrastruct.spec;

import com.eskcti.algafoodapi.domain.models.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {
    public static Specification<Restaurant> withFreeShipping() {
        return (root, query, builder) -> builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withSimilarName(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }
}
