package com.eskcti.algafoodapi.infrastruct.repositories;

import com.eskcti.algafoodapi.domain.models.Restaurant;
import com.eskcti.algafoodapi.domain.repositories.RestaurantRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {
    @PersistenceContext
    private EntityManager manager;

    public List<Restaurant> find(String name,
                                 BigDecimal shippingFeeInitial, BigDecimal shippingFeeFinal) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        Predicate namePredicate = builder.like(root.get("name"), "%" + name + "%");
        Predicate shippingInitial = builder.greaterThanOrEqualTo(root.get("shippingFee"), shippingFeeInitial);
        Predicate shippingFinal = builder.lessThanOrEqualTo(root.get("shippingFee"), shippingFeeFinal);

        criteria.where(namePredicate, shippingInitial, shippingFinal);

        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();

        //        var jpql = "from Restaurant where name like :name " +
//                "and shippingFee between :shippingFeeInitial and :shippingFeeFinal";

        // consulta dinâmica
//        var jpql = new StringBuilder();
//        var params = new HashMap<String, Object>();
//        jpql.append("from Restaurant where 0 = 0 ");
//
//        if(StringUtils.hasLength(name)) {
//            jpql.append("and name like :name ");
//            params.put("name", "%" + name + "%");
//        }
//
//        if (shippingFeeInitial != null) {
//            jpql.append("and shippingFee >= :shippingFeeInitial ");
//            params.put("shippingFeeInitial", shippingFeeInitial);
//        }
//
//        if (shippingFeeFinal != null) {
//            jpql.append("and shippingFee <= :shippingFeeFinal ");
//            params.put("shippingFeeFinal", shippingFeeFinal);
//        }
//
////        return manager.createQuery(jpql.toString(), Restaurant.class)
////                .setParameter("name", "%" + name + "%")
////                .setParameter("shippingFeeInitial", shippingFeeInitial)
////                .setParameter("shippingFeeFinal", shippingFeeFinal)
////                .getResultList();
//
//        TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);
//        params.forEach((key, value) -> query.setParameter(key, value));
//        return query.getResultList();
    }
}
