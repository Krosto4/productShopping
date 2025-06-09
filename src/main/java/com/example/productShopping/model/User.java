package com.example.productShopping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 50)
    public String name;

    @NotBlank(message = "Surname is required")
    @Size(min = 1, max = 50)
    public String surname;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Size(min = 1, max = 50)
    @Column(unique = true)
    public String email;

    @NotBlank(message = "Username is required")
    @Size(min = 1, max = 50)
    @Column(unique = true)
    public String username;

    @NotBlank(message = "Password is required")
    @Size(min = 1)
    public String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CartItem> cartItems = new ArrayList<>();
}
