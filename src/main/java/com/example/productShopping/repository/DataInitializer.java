package com.example.productShopping.repository;

import com.example.productShopping.model.Product;
import com.example.productShopping.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void run() {
        if (productRepository.count() == 0) {
            User user = createUser("Arzu", "Adigozalli", "testgmail@gmail.com", "admin", "admin");
            userRepository.save(user);

            List<Product> products = new ArrayList<>();
            products.add(createProduct("Apple", "iPhone 14", "Smartphones", "Latest Apple iPhone with A15 Bionic chip", 999.0, 5, "https://example.com/iphone14.jpg", user));
            products.add(createProduct("Samsung", "Galaxy S23", "Smartphones", "Flagship Samsung phone with AMOLED display", 899.0, 4, "https://example.com/galaxys23.jpg", user));
            products.add(createProduct("Sony", "WH-1000XM5", "Headphones", "Noise-cancelling wireless headphones", 349.0, 5, "https://example.com/sonywh1000xm5.jpg", user));
            products.add(createProduct("Apple", "MacBook Pro 16", "Laptops", "Powerful laptop with M1 Pro chip", 2499.0, 5, "https://example.com/macbookpro16.jpg", user));
            products.add(createProduct("Dell", "XPS 13", "Laptops", "Compact ultrabook with InfinityEdge display", 1199.0, 4, "https://example.com/dellxps13.jpg", user));
            products.add(createProduct("GoPro", "Hero 11", "Cameras", "Action camera with 5.3K video recording", 499.0, 4, "https://example.com/goprohero11.jpg", user));
            products.add(createProduct("Apple", "AirPods Pro 2", "Earbuds", "Wireless earbuds with active noise cancellation", 249.0, 5, "https://example.com/airpodspro2.jpg", user));
            products.add(createProduct("Samsung", "Galaxy Watch 5", "Smartwatches", "Smartwatch with health tracking features", 279.0, 4, "https://example.com/galaxywatch5.jpg", user));
            products.add(createProduct("Amazon", "Echo Dot 5th Gen", "Smart Home", "Compact smart speaker with Alexa", 49.0, 4, "https://example.com/echodot5.jpg", user));
            products.add(createProduct("Fitbit", "Charge 5", "Fitness Trackers", "Advanced fitness tracker with stress management", 149.0, 4, "https://example.com/fitbitcharge5.jpg", user));



            productRepository.saveAll(products);

            System.out.println("БД заполнена стартовыми данными.");
        } else {
            System.out.println("БД уже содержит данные.");
        }
    }

    private User createUser(String name, String surname, String email, String username, String password) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    private Product createProduct(String brand, String model, String category, String description, double price, int rate, String imageUrl, User user) {
        Product product = new Product();
        product.setBrand(brand);
        product.setModel(model);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setRate(rate);
        product.setImageUrl(imageUrl);
        product.setUser(user);
        return product;
    }
}
