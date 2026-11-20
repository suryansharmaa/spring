package com.backend.spring.JPA.repository;

import com.backend.spring.JPA.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long id);
    Optional<Orders> findByIdAndUserId(Long orderId, Long userId);
}
