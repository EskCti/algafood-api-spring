package com.eskcti.algafoodapi.domain.repositories;

import com.eskcti.algafoodapi.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
