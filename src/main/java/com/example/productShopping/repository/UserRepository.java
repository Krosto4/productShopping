package com.example.productShopping.repository;

import com.example.productShopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<Object> findEyUsername(String username);

    Optional<Object> findByUsername(String username);
}
