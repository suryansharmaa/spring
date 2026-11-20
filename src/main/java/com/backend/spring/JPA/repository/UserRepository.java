package com.backend.spring.JPA.repository;

import com.backend.spring.JPA.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Override
    Page<Users> findAll(Pageable pageable);
    Optional<Users> findByEmail(String email);
}
