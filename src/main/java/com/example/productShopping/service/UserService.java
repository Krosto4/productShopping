package com.example.productShopping.service;

import com.example.productShopping.dto.UserProfileDTO;
import com.example.productShopping.model.User;
import com.example.productShopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

        public UserProfileDTO getUserProfile (String username) {
            User user = (User) userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return new UserProfileDTO (user.getName(), user.getSurname(), user.getEmail(),user.getUsername());

        }
    }
}
