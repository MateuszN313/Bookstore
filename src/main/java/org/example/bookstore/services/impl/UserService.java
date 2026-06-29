package org.example.bookstore.services.impl;

import lombok.AllArgsConstructor;
import org.example.bookstore.models.User;
import org.example.bookstore.services.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {
    @Override
    public List<User> findAllUsers() {
        return List.of();
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public void removeUser(String id, String loggedUserId) {

    }

    @Override
    public User findByLogin(String login) {
        return null;
    }
}
