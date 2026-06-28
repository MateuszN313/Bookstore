package org.example.bookstore.services;

import org.example.bookstore.models.Book;
import org.example.bookstore.models.BookAmount;

public interface ICartService {
    BookAmount addToCart(Book book, int amount);

    void removeFromCart(String bookId);
}
