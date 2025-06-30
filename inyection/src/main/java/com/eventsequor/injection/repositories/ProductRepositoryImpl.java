package com.eventsequor.injection.repositories;

import com.eventsequor.injection.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.List;

@Primary
@Repository("productList")
public class ProductRepositoryImpl implements IProductRepository {

    private final List<Product> productList;

    public ProductRepositoryImpl() {
        this.productList = Arrays.asList(
                new Product(1L, "Box", 10L),
                new Product(2L, "Wheel", 20L),
                new Product(3L, "Processor", 30L),
                new Product(4L, "RAM", 25L)
        );
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(Long id) {
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }
}
