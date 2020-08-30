package com.example.recruitment_assessment.service;


import com.example.recruitment_assessment.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    void deleteProductById(Long id);
    Optional<Product> addNewProduct(Product product);
}
