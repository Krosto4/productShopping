package com.example.productShopping.controller;

import com.example.productShopping.dto.ProductDTO;
import com.example.productShopping.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(Principal principal, @Valid @RequestBody ProductDTO productDto) {
        return ResponseEntity.ok(productService.addProduct (principal.getName(), productDto));
    }

    @GetMapping

    public ResponseEntity<List<ProductDTO>> getUserProducts (Principal principal) {
        return ResponseEntity.ok(productService.getUserProducts(principal.getName()));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(
            Principal principal,
            @PathVariable Long productId
    ) {
        productService.deleteProduct(principal.getName(), productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
            Principal principal,
            @PathVariable Long productId,
            @Valid @RequestBody ProductDTO productDto
    ) {
        return  ResponseEntity.ok(productService.updateProduct(principal.getName(), productId, productDto));
    }

}

