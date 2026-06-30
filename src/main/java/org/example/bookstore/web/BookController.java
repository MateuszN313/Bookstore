package org.example.bookstore.web;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.Book;
import org.example.bookstore.services.IBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {
    private final IBookService bookService;

    @GetMapping
    public List<Book> list(){
        return this.bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable String id){
        return this.bookService.findById(id);
    }


}
