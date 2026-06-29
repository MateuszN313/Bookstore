package org.example.bookstore.services.impl;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.Book;
import org.example.bookstore.models.BookAmount;
import org.example.bookstore.services.ICartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CartService implements ICartService {
    @Override
    public BookAmount addToCart(Book book, int amount) {
        return null;
    }

    @Override
    public void removeFromCart(String bookId) {

    }
}
