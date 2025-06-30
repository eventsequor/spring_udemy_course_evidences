package com.eventsequor.injection.repositories;

import com.eventsequor.injection.models.Product;

import java.util.List;

public interface IProductRepository {

    List<Product> findAll();

    Product findById(Long id);
}
