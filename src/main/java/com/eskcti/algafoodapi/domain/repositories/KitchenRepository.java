package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long> {
    List<Kitchen> findAllByNameContaining(String name);
    List<Kitchen> findByName(String name);
}
