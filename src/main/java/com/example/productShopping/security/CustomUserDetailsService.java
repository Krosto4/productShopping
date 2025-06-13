package com.example.productShopping.security;

import com.example.productShopping.model.User;
import com.example.productShopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails. UserDetailsService;

import org.springframework.security.core.userdetails. UsernameNotFoundException;

import org.springframework.stereotype.Service;

// Убираем псевдоним и используем полное имя класса, где необходимо

@Service
@RequiredArgsConstructor

public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

// Поиск пользователя в базе данных

        User user = (User) userRepository.findEyUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username)
                        );
// Создаем UserDetails, используя полный путь к классу

        return org.springframework.security.core.userdetails.User
                .withUsername (user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}