package org.example.bookstore.services;

import org.example.bookstore.models.Book;

import java.util.List;

public interface IBookService {

    List<Book> findAllBooks();

    Book findById(String id);

    Book addBook(Book book);

    void removeBook(String id);
}
