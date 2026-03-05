package com.klu.repository;

import com.klu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllSortedByPrice();

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findExpensiveProducts(@Param("price") double price);

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategoryJPQL(@Param("category") String category);
}
