package org.example.bookstore.services.impl;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.Book;
import org.example.bookstore.services.IBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BookService implements IBookService {
    @Override
    public List<Book> findAllBooks() {
        return List.of();
    }

    @Override
    public Book findById(String id) {
        return null;
    }

    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public void removeBook(String id) {

    }
}
