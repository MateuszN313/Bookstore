package org.example.bookstore.services.impl;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.Book;
import org.example.bookstore.repositories.BookJpaRepository;
import org.example.bookstore.services.IBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BookService implements IBookService {
    private final BookJpaRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(String id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No book with such ID"));
    }

    @Override
    public Book addBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public void removeBook(String id) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No book with such ID"));

        this.bookRepository.delete(book);
    }
}
