package com.example.recruitment_assessment.repository;

import com.example.recruitment_assessment.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;


@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository {


    @PersistenceContext
    private EntityManager entityManager;


    public Optional<Product> getProductById(Long id) {
        return entityManager
                .createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return entityManager
                .createQuery("SELECT p FROM Product p ORDER BY p.seq ASC", Product.class)
                .getResultList();
    }

    @Override
    public void deleteProductById(Long id) {
        entityManager
                .createQuery("DELETE FROM Product p WHERE p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        if (nonNull(product.getId())) {
            entityManager.merge(product);
        } else {
            entityManager.persist(product);
        }
        return product;
    }
}
