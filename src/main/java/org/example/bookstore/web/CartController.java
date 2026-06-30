package org.example.bookstore.web;

import lombok.AllArgsConstructor;
import org.example.bookstore.dto.CartRequest;
import org.example.bookstore.models.Cart;
import org.example.bookstore.models.User;
import org.example.bookstore.services.ICartService;
import org.example.bookstore.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {
    private final ICartService cartService;
    private final IUserService userService;

    @PostMapping
    public Cart saveCart(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartRequest cartRequest){
        String login = userDetails.getUsername();
        User user = this.userService.findByLogin(login);

        return this.cartService.saveCart(user.getId(), cartRequest.bookId(), cartRequest.amount());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartRequest cartRequest){
        String login = userDetails.getUsername();
        User user = this.userService.findByLogin(login);

        this.cartService.removeFromCart(user.getId(), cartRequest.bookId());
        return ResponseEntity.noContent().build();
    }
}
