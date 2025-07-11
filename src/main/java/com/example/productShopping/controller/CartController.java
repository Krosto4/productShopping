package com.example.productShopping.controller;

import com.example.productShopping.dto.CartItemDto;
import com.example.productShopping.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getUserCart (Principal principal) {
        if (principal == null) {
            return ResponseEntity.status (HttpStatus.UNAUTHORIZED).build();
        }
        List<CartItemDto> cart = cartService.getUserCart(principal.getName());
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<CartItemDto> addToCart(
            Principal principal,
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") Integer quantity
    ) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        System.out.println("Добавление в корзину для пользователя:" + principal.getName());
        CartItemDto cartItem = cartService.addToCart(principal.getName(), productId, quantity);

        if (cartItem != null) {
            return ResponseEntity.ok(cartItem);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Ошибка добавления
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeFromCart(Principal principal, @PathVariable Long productId) {
        if (principal == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        cartService.removeFromCart(principal.getName(), productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateCartItemQuantity( Principal principal, @PathVariable Long productId, @RequestParam Integer quantity){
        if (principal == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
        CartItemDto updatedItem = cartService.updateCartItemQuantity(principal.getName(), productId, quantity);

        return updatedItem != null
            ? ResponseEntity.ok(updatedItem)
            : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            cartService.clearCart(principal.getName());
            return ResponseEntity.noContent().build(); // Возвращаем 204 No Content для успешного удаления
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Обработка возможных исключений
        }
    }
}