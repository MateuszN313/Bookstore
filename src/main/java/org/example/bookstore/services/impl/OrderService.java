package org.example.bookstore.services.impl;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.Order;
import org.example.bookstore.services.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderService implements IOrderService {
    @Override
    public List<Order> findAllOrders() {
        return List.of();
    }

    @Override
    public List<Order> findUserOrders() {
        return List.of();
    }

    @Override
    public Order findById(String id) {
        return null;
    }

    @Override
    public Order orderBook(String userId, String bookId) {
        return null;
    }

    @Override
    public Order orderCart(String userId) {
        return null;
    }

    @Override
    public Order changeStatus(String orderId, String status) {
        return null;
    }
}
