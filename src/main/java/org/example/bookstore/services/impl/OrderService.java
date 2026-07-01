package org.example.bookstore.services.impl;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.*;
import org.example.bookstore.repositories.*;
import org.example.bookstore.services.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final OrderJpaRepository orderRepository;
    private final BookAmountJpaRepository bookAmountRepository;

    private final UserJpaRepository userRepository;
    private final BookJpaRepository bookRepository;
    private final CartJpaRepository cartRepository;

    @Override
    public List<Order> findAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> findUserOrders(String userId) {
        List<Order> all = this.orderRepository.findAll();
        List<Order> user = new ArrayList<>();

        for(Order order : all){
            if(order.getUser().getId().equals(userId))
                user.add(order);
        }

        return user;
    }

    @Override
    public Order findById(String id) {
        return this.orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No order with such ID"));
    }

    @Override
    public Order orderBook(String userId, String bookId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("No user with such ID"));

        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("No book with such ID"));

        BookAmount bookAmount = new BookAmount(UUID.randomUUID().toString(), book, 1);
        BookAmount savedBookAmount = this.bookAmountRepository.save(bookAmount);

        Order order = new Order(UUID.randomUUID().toString(), user, LocalDateTime.now().toString(), Status.NEW, Set.of(savedBookAmount));
        return this.orderRepository.save(order);
    }

    @Override
    public Order orderCart(String userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("No user with such ID"));

        List<Cart> cart = this.cartRepository.findAllByUser_Id(userId);
        if(cart.isEmpty())
            throw new IllegalStateException("User has no cart");

        Set<BookAmount> books = new HashSet<>();
        for(Cart c : cart){
            BookAmount bookAmount = this.bookAmountRepository.findById(c.getBookAmount().getId())
                            .orElseThrow(() -> new IllegalStateException("No book data in cart"));
            books.add(bookAmount);
            this.cartRepository.delete(c);
        }

        Order order = new Order(UUID.randomUUID().toString(), user, LocalDateTime.now().toString(), Status.NEW, books);
        return this.orderRepository.save(order);
    }

    @Override
    public Order changeStatus(String orderId, String statusName) {
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("No order with such ID"));

        Status status = Status.valueOf(statusName);

        order.setStatus(status);
        return this.orderRepository.save(order);
    }
}
