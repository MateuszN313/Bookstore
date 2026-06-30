package org.example.bookstore.services.impl;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.User;
import org.example.bookstore.repositories.UserJpaRepository;
import org.example.bookstore.services.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserJpaRepository userRepository;

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
}
