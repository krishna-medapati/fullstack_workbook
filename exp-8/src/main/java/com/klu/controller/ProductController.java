package com.klu.controller;

import com.klu.entity.Product;
import com.klu.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        List<Product> products = productRepository.findByCategory(category);
        if (products.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterByPrice(@RequestParam double min, @RequestParam double max) {
        List<Product> products = productRepository.findByPriceBetween(min, max);
        if (products.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> getSortedByPrice() {
        return ResponseEntity.ok(productRepository.findAllSortedByPrice());
    }

    @GetMapping("/expensive/{price}")
    public ResponseEntity<List<Product>> getExpensive(@PathVariable double price) {
        List<Product> products = productRepository.findExpensiveProducts(price);
        if (products.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(products);
    }
}
