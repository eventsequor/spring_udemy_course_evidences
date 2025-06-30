package com.eventsequor.injection.services;

import com.eventsequor.injection.models.Product;
import com.eventsequor.injection.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    @Qualifier("getProductRepositoryJson")
    private IProductRepository repository;

    @Value("${config.tax}")
    private Double tax;

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(p -> {
                    Double price = p.getPrice() * tax;
                    /*Product newProduct = (Product) p.clone();
                    newProduct.setPrice(price.longValue());
                    return newProduct;*/
                    p.setPrice(price.longValue());
                    return p;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id);
    }
}
