package com.eskcti.algafoodapi.infrastruct.spec;

import com.eskcti.algafoodapi.domain.filter.OrderFilter;
import com.eskcti.algafoodapi.domain.models.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class OrderSpecs {
    public static Specification<Order> withFilter(OrderFilter orderFilter) {
        return (root, query, builder) -> {
            if (Order.class.equals(query.getResultType())) {
                root.fetch("restaurant").fetch("kitchen");
                root.fetch("customer");
            }

            var predicates = new ArrayList<Predicate>();

            if(orderFilter.getCustomerId() != null) {
                predicates.add(builder.equal(root.get("customer"), orderFilter.getCustomerId()));
            }

            if(orderFilter.getRestaurantId() != null) {
                predicates.add(builder.equal(root.get("restaurant"), orderFilter.getRestaurantId()));
            }

            if(orderFilter.getDateFrom() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), orderFilter.getDateFrom()));
            }

            if(orderFilter.getDateTo() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), orderFilter.getDateTo()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
