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

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {
    private final ICartService cartService;
    private final IUserService userService;

    @GetMapping
    public List<Cart> listByUser(@AuthenticationPrincipal UserDetails userDetails){
        String login = userDetails.getUsername();
        User user = this.userService.findByLogin(login);

        return this.cartService.getCartByUserId(user.getId());
    }

    @PostMapping
    public Cart saveCart(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartRequest cartRequest){
        String login = userDetails.getUsername();
        User user = this.userService.findByLogin(login);

        return this.cartService.saveCart(user.getId(), cartRequest.bookId(), cartRequest.amount());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.cartService.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }
}
