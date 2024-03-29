package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
