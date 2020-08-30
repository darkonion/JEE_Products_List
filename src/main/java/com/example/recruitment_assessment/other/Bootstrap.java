package com.example.recruitment_assessment.other;

import com.example.recruitment_assessment.entity.Product;
import com.example.recruitment_assessment.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class Bootstrap {

    private final ProductService productService;
    private final Logger log;

    @Inject
    public Bootstrap(ProductService productService, Logger log) {
        this.productService = productService;
        this.log = log;
    }

    public void bootstrap(@Observes @Initialized(ApplicationScoped.class) Object init) {
        log.info("Initializing bootstrap...");
        createInitialData()
                .forEach(p -> productService.addNewProduct(p));
    }

    private List<Product> createInitialData() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().label("Samsung Galaxy Tab").details("A 10,1").build());
        products.add(Product.builder().label("Lenovo Tab3").details("A8-50F 16GB").build());
        products.add(Product.builder().label("Huawei MediaPad").details("M3 8\" 32GB").build());
        products.add(Product.builder().label("Apple IPad").details("32GB White").build());
        products.add(Product.builder().label("Overmax Qualcore").details("1026 16GB").build());
        products.add(Product.builder().label("Lenovo Legion y540").details("17 GeForce GTX").build());
        products.add(Product.builder().label("Samsung S8").details("256GB").build());
        products.add(Product.builder().label("Logitech M280").details("Wireless Optic").build());
        products.add(Product.builder().label("Audient iD8").details("2-channels").build());
        products.add(Product.builder().label("Phillips 243V7Q").details("23,8 IPS 60Hz").build());
        return products;
    }

}
