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


@ApplicationScoped
public class SequenceServiceImpl implements SequenceService {

    private final ProductRepository repository;
    private final Logger log;

    @Inject
    public SequenceServiceImpl(ProductRepository repository, Logger log) {
        this.repository = repository;
        this.log = log;
    }

    @Override
    @Transactional
    public boolean up(Long id) {
        if (!productCheck(id)) return false;

        List<Product> productList = repository.getAll();
        Product product = repository.getProductById(id).get();

        int index = productList.indexOf(product);
        if (index < 1) {
            log.info("Product is already at the top of list");
            return false;
        }

        log.info(format("Moving up product with id: %d", id));
        Product productToSwap = productList.get(--index);
        seqSwap(product, productToSwap);

        repository.saveOrUpdateProduct(product);
        repository.saveOrUpdateProduct(productToSwap);

        log.info("Movement succeeded");
        return true;
    }

    @Override
    @Transactional
    public boolean down(Long id) {
        if (!productCheck(id)) return false;

        List<Product> productList = repository.getAll();
        Product product = repository.getProductById(id).get();

        int index = productList.indexOf(product);
        if (index >= productList.size() - 1) {
            log.info("Product is already at the bottom of list");
            return false;
        }

        log.info(format("Moving down product with id: %d", id));
        Product productToSwap = productList.get(++index);
        seqSwap(product, productToSwap);

        repository.saveOrUpdateProduct(product);
        repository.saveOrUpdateProduct(productToSwap);

        log.info("Movement succeeded");
        return true;
    }

    @Override
    @Transactional
    public boolean top(Long id) {
        if (!productCheck(id)) return false;

        List<Product> productList = repository.getAll();
        Product product = repository.getProductById(id).get();

        if (productList.get(0).getId() == id) {
            log.info("Product is already at the top of list");
            return false;
        }

        log.info(format("Moving to the top product with id: %d", id));
        productList.remove(product);
        productList.add(0, product);

        log.info("Rewriting products sequence");
        sequenceRewrite(productList);

        productList.stream().forEach(repository::saveOrUpdateProduct);

        log.info("Movement succeeded");
        return true;
    }

    @Override
    @Transactional
    public boolean bottom(Long id) {
        if (!productCheck(id)) return false;

        List<Product> productList = repository.getAll();
        Product product = repository.getProductById(id).get();

        if (productList.get(productList.size() - 1).getId() == id) {
            log.info("Product is already at the bottom of list");
            return false;
        }

        log.info(format("Moving to the bottom product with id: %d", id));
        productList.remove(product);
        productList.add(product);

        log.info("Rewriting products sequence");
        sequenceRewrite(productList);

        productList.stream().forEach(repository::saveOrUpdateProduct);

        log.info("Movement succeeded");
        return true;
    }


    //Helper methods
    private void seqSwap(Product product, Product productToSwap) {
        int tempSeq = productToSwap.getSeq();
        productToSwap.setSeq(product.getSeq());
        product.setSeq(tempSeq);
    }

    private boolean productCheck(Long id) {
        Optional<Product> temp = repository.getProductById(id);
        if (temp.isPresent() && temp.get().getId() != null) {
            return true;
        }
        log.info(format("Product with id: %d not found", id));
        return false;
    }

    private void sequenceRewrite(List<Product> productList) {
        for (int i = 0; i < productList.size(); i++) {
            productList.get(i).setSeq(i + 1);
        }
    }
}
