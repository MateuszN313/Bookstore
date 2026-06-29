package org.example.bookstore.repositories;

import org.example.bookstore.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusJpaRepository extends JpaRepository<Status, String> {
    Optional<Status> findByName(String name);
}
