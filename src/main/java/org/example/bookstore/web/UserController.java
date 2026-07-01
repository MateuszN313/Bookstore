package org.example.bookstore.web;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.User;
import org.example.bookstore.services.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/{id}")
    public User get(@PathVariable String id){
        return this.userService.findById(id);
    }
}
