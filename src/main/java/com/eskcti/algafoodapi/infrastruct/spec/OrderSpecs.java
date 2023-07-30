package com.eskcti.algafoodapi.infrastruct.spec;

import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.filters.OrderFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderSpecs {
    public static Specification<Order> withFilter(OrderFilter orderFilter) {
        return (root, query, builder) -> {
            root.fetch("restaurant").fetch("kitchen");
            root.fetch("customer");

            var predicates = new ArrayList<Predicate>();

            if(orderFilter.getCustomerId() != null) {
                predicates.add(builder.equal(root.get("customer"), orderFilter.getCustomerId()));
            }

            if(orderFilter.getRestaurantId() != null) {
                predicates.add(builder.equal(root.get("restaurant"), orderFilter.getRestaurantId()));
            }

            if(orderFilter.getDateFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("created_at"), orderFilter.getDateFrom()));
            }

            if(orderFilter.getDateTo() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("created_at"), orderFilter.getDateTo()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Restaurant> withSimilarName(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }
}
