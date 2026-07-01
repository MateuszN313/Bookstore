package org.example.bookstore.services.impl;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.Role;
import org.example.bookstore.models.User;
import org.example.bookstore.repositories.RoleJpaRepository;
import org.example.bookstore.repositories.UserJpaRepository;
import org.example.bookstore.services.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserJpaRepository userRepository;
    private final RoleJpaRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No user with such ID"));
    }

    @Override
    public void removeUser(String id, String loggedUserId) {
        if(id.equals(loggedUserId))
            throw new IllegalArgumentException("You can't delete yourself");

        Optional<User> opt = this.userRepository.findById(id);
        if(opt.isEmpty())
            throw new IllegalArgumentException("No user with such ID");

        this.userRepository.deleteById(id);
    }

    @Override
    public User findByLogin(String login) {
        return this.userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("No user with such login"));
    }

    @Override
    public void register(String login, String password) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login can't be empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password can't be empty");
        }
        if (this.userRepository.findByLogin(login).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("No ROLE_USER found"));
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .login(login)
                .passwordHash(passwordEncoder.encode(password))
                .roles(Set.of(userRole))
                .build();
        this.userRepository.save(user);
    }
}
