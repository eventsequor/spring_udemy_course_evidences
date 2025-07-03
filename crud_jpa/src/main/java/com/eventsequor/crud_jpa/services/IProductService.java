package com.eventsequor.crud_jpa.services;

import com.eventsequor.crud_jpa.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> delete(Product product);

    Optional<Product> delete(Long id);

    boolean existBySku(String sku);
}
