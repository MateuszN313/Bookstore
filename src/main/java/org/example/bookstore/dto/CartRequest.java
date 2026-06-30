package org.example.bookstore.dto;

public record CartRequest(String bookId, int amount) {
}
