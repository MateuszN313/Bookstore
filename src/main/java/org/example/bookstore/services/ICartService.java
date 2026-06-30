package org.example.bookstore.services;

import org.example.bookstore.models.Cart;

import java.util.List;

public interface ICartService {
    List<Cart> getCartByUserId(String userId);

    Cart saveCart(String userId, String bookId, int amount);

    void removeFromCart(String userId, String bookId);
}
