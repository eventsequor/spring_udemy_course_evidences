package com.eventsequor.backend_crud_products.repositories;

import com.eventsequor.backend_crud_products.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {
}
