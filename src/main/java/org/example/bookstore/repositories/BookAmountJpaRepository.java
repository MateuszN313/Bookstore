package org.example.bookstore.repositories;

import org.example.bookstore.models.BookAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAmountJpaRepository extends JpaRepository<BookAmount, String> {
}
