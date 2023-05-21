package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
