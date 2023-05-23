package com.eskcti.algafoodapi.infrastruct.repositories;

import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {
    @PersistenceContext
    private EntityManager manager;

    public List<Restaurant> find(String name,
                                 BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal) {
        var jpql = "from Restaurant where name like :name " +
                "and shippingFee between :shippingFeeInitial and :shippingFeeFinal";

        return manager.createQuery(jpql, Restaurant.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("shippingFeeInitial", shippingFeeInitial)
                .setParameter("shippingFeeFinal", shippingFeeFinal)
                .getResultList();
    }
}
