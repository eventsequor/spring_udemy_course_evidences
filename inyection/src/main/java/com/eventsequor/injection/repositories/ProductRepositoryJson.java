package com.eventsequor.injection.repositories;

import com.eventsequor.injection.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProductRepositoryJson implements IProductRepository {


    private List<Product> productList;

    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productList = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(Long id) {
        if (productList == null)
            return null;
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }
}
