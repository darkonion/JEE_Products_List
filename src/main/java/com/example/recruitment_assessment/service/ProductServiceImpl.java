package com.example.recruitment_assessment.service;

import com.example.recruitment_assessment.entity.Product;
import com.example.recruitment_assessment.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Logger log;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, Logger log) {
        this.productRepository = productRepository;
        this.log = log;
    }

    @Override
    public List<Product> getAll() {
        log.info("Fetching full list of products from database");

        List<Product> productList = ofNullable(productRepository.getAll())
                .orElse(emptyList());

        if (productList.isEmpty()) {
            log.info("List is empty!");
        } else {
            log.info(format("Pulled %d results from database", productList.size()));
        }
        return productList;
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        log.info(format("Deleting Product with id: %d", id));
        productRepository.deleteProductById(id);
        log.info(format("Product with id: %d, no longer exists in database", id));
    }

    @Override
    @Transactional
    public Optional<Product> addNewProduct(Product product) {
        log.info("Adding new product" + product);
        setOrder(product);
        Product savedProduct = productRepository.saveOrUpdateProduct(product);
        if (isNull(savedProduct.getId())) {
            log.warning("Something goes wrong with product persistence");
        } else {
            log.info(format("Product has been successfully added, generated id: %d, generated sequence: %d", savedProduct.getId(), savedProduct.getSeq()));
        }
        return ofNullable(savedProduct);
    }

    //looking for the highest sequence in product list
    private void setOrder(Product product) {
        int i = productRepository.getAll()
                .stream()
                .mapToInt(Product::getSeq)
                .filter(n -> n > 0)
                .max()
                .orElse(0);
        product.setSeq(i + 1);
    }
}
