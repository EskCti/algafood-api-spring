package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}
