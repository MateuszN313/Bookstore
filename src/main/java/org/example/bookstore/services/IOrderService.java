package org.example.bookstore.services;

import org.example.bookstore.models.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAllOrders();

    List<Order> findUserOrders();

    Order findById(String id);

    Order orderBook(String userId, String bookId);

    Order orderCart(String userId);

    Order changeStatus(String orderId, String status);
}
