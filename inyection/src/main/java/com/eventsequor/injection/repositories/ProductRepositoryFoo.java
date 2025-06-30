package com.eventsequor.injection.repositories;

import com.eventsequor.injection.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository("productFoo")
public class ProductRepositoryFoo implements IProductRepository{
    @Override
    public List<Product> findAll() {
        return Collections.singletonList(new Product(1L, "Laptop", 100L));
    }

    @Override
    public Product findById(Long id) {
        return new Product(1L, "Laptop", 1000L);
    }
}
