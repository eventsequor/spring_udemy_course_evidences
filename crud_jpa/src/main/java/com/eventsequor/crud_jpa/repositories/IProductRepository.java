package com.eventsequor.crud_jpa.repositories;

import com.eventsequor.crud_jpa.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, Long> {

    boolean existsBySku(String sku);
}
