package org.example.bookstore.repositories;

import org.example.bookstore.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartJpaRepository extends JpaRepository<Cart, String> {
    List<Cart> findAllByUser_Id(String userId);
}
