package org.example.bookstore.web;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.User;
import org.example.bookstore.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserAdminController {
    private final IUserService userService;

    @GetMapping
    public List<User> list(){
        return this.userService.findAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id){
        String login = userDetails.getUsername();
        User user = this.userService.findByLogin(login);

        this.userService.removeUser(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}
