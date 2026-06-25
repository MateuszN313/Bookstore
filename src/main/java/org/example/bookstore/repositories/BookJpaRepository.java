package org.example.bookstore.repositories;

import org.example.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<Book, String> {
}
