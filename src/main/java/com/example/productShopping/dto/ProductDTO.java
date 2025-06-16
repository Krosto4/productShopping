package com.example.productShopping.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String brand;
    private String model;
    private int price;
    private int rate;
    private String description;
    private String imageUrl;
    private String category;
}
