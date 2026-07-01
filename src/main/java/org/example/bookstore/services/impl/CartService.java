package org.example.bookstore.services.impl;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.Book;
import org.example.bookstore.models.BookAmount;
import org.example.bookstore.models.Cart;
import org.example.bookstore.models.User;
import org.example.bookstore.repositories.BookAmountJpaRepository;
import org.example.bookstore.repositories.BookJpaRepository;
import org.example.bookstore.repositories.CartJpaRepository;
import org.example.bookstore.repositories.UserJpaRepository;
import org.example.bookstore.services.ICartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CartService implements ICartService {
    private final CartJpaRepository cartRepository;
    private final BookAmountJpaRepository bookAmountRepository;

    private final UserJpaRepository userRepository;
    private final BookJpaRepository bookRepository;

    @Override
    public List<Cart> getCartByUserId(String userId) {
        return this.cartRepository.findAllByUser_Id(userId);
    }

    @Override
    public Cart saveCart(String userId, String bookId, int amount) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("No user with such ID"));

        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("No book with such ID"));

        List<Cart> userCart = this.cartRepository.findAllByUser_Id(userId);

        Optional<Cart> opt = userCart.stream()
                .filter(cart -> cart.getBookAmount().getBook().getId().equals(bookId))
                .findFirst();

        if(opt.isEmpty()){
            Cart toSave;

            BookAmount bookAmount = new BookAmount(UUID.randomUUID().toString(), book, amount);
            BookAmount savedBookAmount = this.bookAmountRepository.save(bookAmount);

            toSave = new Cart(UUID.randomUUID().toString(), user, savedBookAmount);

            return this.cartRepository.save(toSave);
        }else{
            Cart cart = opt.get();
            BookAmount bookAmount = this.bookAmountRepository.findById(cart.getBookAmount().getId())
                    .orElseThrow(() -> new IllegalStateException("no book data"));
            bookAmount.setAmount(amount);
            this.bookAmountRepository.save(bookAmount);

            return this.cartRepository.findById(cart.getId()).get();
        }
    }

    @Override
    public void removeFromCart(String cartId) {
        Cart cart = this.cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("no cart with such ID"));

        BookAmount bookAmount = cart.getBookAmount();

        this.cartRepository.delete(cart);
        this.bookAmountRepository.delete(bookAmount);
    }
}
