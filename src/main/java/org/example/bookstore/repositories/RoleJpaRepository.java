package org.example.bookstore.repositories;

import org.example.bookstore.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
}
