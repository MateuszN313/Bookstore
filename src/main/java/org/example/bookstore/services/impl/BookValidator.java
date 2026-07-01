package org.example.bookstore.services.impl;

import org.example.bookstore.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {
    public void validate(Book book){
        if(book == null) throw new IllegalArgumentException("Book can't be null");

        requireNonBlank(book.getTitle(), "title field can't be empty");
        requireNonBlank(book.getAuthor(), "author field can't be empty");
        requireNonBlank(book.getCategory(), "category field can't be empty");

        if(book.getPrice() <= 0) throw new IllegalArgumentException("price must be above zero");
        if(book.getYear() <= 0) throw new IllegalArgumentException("year must be above zero");
        if(book.getPages() <= 0) throw new IllegalArgumentException("pages amount must be above zero");
    }

    private void requireNonBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
}
