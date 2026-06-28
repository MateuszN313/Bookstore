package org.example.bookstore.repositories;

import org.example.bookstore.models.OrderBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBooksJpaRepository extends JpaRepository<OrderBooks, String> {
}
