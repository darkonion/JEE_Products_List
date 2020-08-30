package com.example.recruitment_assessment.repository;



import com.example.recruitment_assessment.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> getProductById(Long id);
    List<Product> getAll();
    void deleteProductById(Long id);
    Product saveOrUpdateProduct(Product product);
}
