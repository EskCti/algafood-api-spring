package com.eskcti.algafoodapi.infrastruct.spec;

import com.eskcti.algafoodapi.domain.models.Restaurant;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantWithFreeShippingSpec implements Specification<Restaurant> {
    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }
}
