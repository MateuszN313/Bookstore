package org.example.bookstore.repositories;

import org.example.bookstore.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
}
