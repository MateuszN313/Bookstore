package org.example.bookstore.web;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.Order;
import org.example.bookstore.models.User;
import org.example.bookstore.services.IOrderService;
import org.example.bookstore.services.IUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    private final IUserService userService;

    @GetMapping("/user")
    public List<Order> listByUser(@AuthenticationPrincipal UserDetails userDetails){
        String login = userDetails.getUsername();
        User user = this.userService.findByLogin(login);

        return this.orderService.findUserOrders(user.getId());
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable String id){
        return  this.orderService.findById(id);
    }

    @PostMapping("book/{id}")
    public Order orderBook(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String bookId){
        String login = userDetails.getUsername();
        User user = this.userService.findByLogin(login);

        return this.orderService.orderBook(user.getId(), bookId);
    }

    @PostMapping
    public Order orderCart(@AuthenticationPrincipal UserDetails userDetails){
        String login = userDetails.getUsername();
        User user = this.userService.findByLogin(login);

        return this.orderService.orderCart(user.getId());
    }
}
