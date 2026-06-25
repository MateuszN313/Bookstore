package org.example.bookstore.repositories;

import org.example.bookstore.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByLogin(String login);
}
