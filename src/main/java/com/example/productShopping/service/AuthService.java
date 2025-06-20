package com.example.productShopping.service;

import com.example.productShopping.dto.LoginRequest;
import com.example.productShopping.dto.RegisterRequest;
import com.example.productShopping.model.User;
import com.example.productShopping.repository.UserRepository;
import com.example.productShopping.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    @Transactional

    public String registerUser (RegisterRequest request) {

// Проверяем, существует ли уже имя пользователя или еmail
        if (userRepository.existsByUsername (request.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

// Создаем нового пользователя
        User user = new User();
        user.setName(request.getName());
        user.setSurname (request.getSurname());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword (passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";

    }

    public String loginUser (LoginRequest request) {
            Authentication authentication = authenticationManager.authenticate(

            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
    );

        SecurityContextHolder.getContext().setAuthentication (authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    public void logoutUser() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                SecurityContextHolder.clearContext(); // Очистка контекста безопасности
            }

}
}
