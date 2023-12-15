package com.eskcti.algafoodapi.infrastruct.services;

import com.eskcti.algafoodapi.domain.filter.DailySalesFilter;
import com.eskcti.algafoodapi.domain.models.DailySales;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.models.OrderStatus;
import com.eskcti.algafoodapi.domain.services.SaleQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySales> queryDailySales(DailySalesFilter filter, String timeOffset) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(DailySales.class);
        var root = query.from(Order.class);

        var functionConvertTzCreatedAt = builder.function(
            "convert_tz", LocalDate.class, root.get("createdAt"),
            builder.literal("+00:00"), builder.literal(timeOffset)
        );

        var functionDateCreatedAt = builder.function("date", LocalDate.class, functionConvertTzCreatedAt);

        var predicates = new ArrayList<Predicate>();

        if(filter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
        }

        if(filter.getDateFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), filter.getDateFrom()));
        }

        if(filter.getDateTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), filter.getDateTo()));
        }

        predicates.add(root.get("orderStatus").in(OrderStatus.CONFIRMED, OrderStatus.DELIVERED));

        var selection = builder.construct(
                DailySales.class,
                functionDateCreatedAt,
                builder.count(root.get("id")),
                builder.sum(root.get("valueTotal"))
        );

        query.select(selection);
        query.groupBy(functionDateCreatedAt);
        query.where(predicates.toArray(new Predicate[0]));

        return manager.createQuery(query).getResultList();
    }
}
