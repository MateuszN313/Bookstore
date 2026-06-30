package org.example.bookstore.web;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.Book;
import org.example.bookstore.services.IBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/book")
@AllArgsConstructor
public class BookAdminController {
    private final IBookService bookService;

    @PostMapping
    public Book create(@RequestBody Book book){
        return this.bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.bookService.removeBook(id);
        return ResponseEntity.noContent().build();
    }
}
