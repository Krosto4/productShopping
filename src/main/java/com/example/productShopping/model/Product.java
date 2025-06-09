package com.example.productShopping.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product brand is required")
    private String brand;

    @NotBlank(message = "Product model is required")
    private String model;

    @NotBlank(message = "Product category is required")
    private String category;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "description can't be null")
    @Size(min = 1, max = 5000, message = "Description must be between 1 and 5000")
    private String description;

    @NotNull(message = "Price can't be null")
    @DecimalMin(value = "0", message = "Price must be at least 0")
    private double price;

    @NotNull(message = "Rate cannot be null")
    @DecimalMin(value = "0", message = "Rate must be at least 0")
    @DecimalMax(value = "5", message = "Rate must be less than or equal to 5")
    private int rate;

    @Column(columnDefinition = "TEXT")
    @NotNull(message = "Image url cannot be null")
    @Size(min = 1, max = 5000, message = "Image url must be between 1 and 5000 characters")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user id")
    @JsonBackReference
    private User user;

}
