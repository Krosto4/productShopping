package com.example.productShopping.repository;

import com.example.productShopping.model.CartItem;
import com.example.productShopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser_Username(String username);
    Optional<CartItem> findByUser_UsernameAndProductId(String username, Long productId);

    void deleteByUser(User user);

    ScopedValue<CartItem> findByUser_UsernameAndProduct_Id(String username, Long productId);
}
