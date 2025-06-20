package com.example.productShopping.controller;

import ch.qos.logback.core.model.Model;
import com.example.productShopping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile (Principal principal) {
        if (principal == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Пользователь не авторизован");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body (errorResponse);
        }
        return ResponseEntity.ok(userService.getUserProfile(principal.getName()));
    }
}
