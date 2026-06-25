package org.example.bookstore.services;

import org.example.bookstore.models.User;

import java.util.List;

public interface IUserService {

    List<User> findAllUsers();

    User findById(String id);

    void deleteUser(String id, String loggedUserId);

    User findByLogin(String login);
}
