package org.example.bookstore.repositories;

import org.example.bookstore.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, String> {
}
