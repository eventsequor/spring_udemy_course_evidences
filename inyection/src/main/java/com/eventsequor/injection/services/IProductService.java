package com.eventsequor.injection.services;

import com.eventsequor.injection.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(Long id);
}
