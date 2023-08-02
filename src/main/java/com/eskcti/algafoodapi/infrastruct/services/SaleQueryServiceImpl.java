package com.eskcti.algafoodapi.infrastruct.services;

import com.eskcti.algafoodapi.domain.filter.DailySalesFilter;
import com.eskcti.algafoodapi.domain.models.DailySales;
import com.eskcti.algafoodapi.domain.models.Order;
import com.eskcti.algafoodapi.domain.services.SaleQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DailySales> queryDailySales(DailySalesFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(DailySales.class);
        var root = query.from(Order.class);

        var functionDateCreatedAt = builder.function("date", Date.class, root.get("createdAt"));

        var selection = builder.construct(
                DailySales.class,
                functionDateCreatedAt,
                builder.count(root.get("id")),
                builder.sum(root.get("valueTotal"))
        );

        query.select(selection);
        query.groupBy(functionDateCreatedAt);

        return manager.createQuery(query).getResultList();
    }
}
