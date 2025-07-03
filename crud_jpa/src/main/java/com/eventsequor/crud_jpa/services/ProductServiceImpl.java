package com.eventsequor.crud_jpa.services;

import com.eventsequor.crud_jpa.entities.Product;
import com.eventsequor.crud_jpa.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            return optionalProduct;

        Product p = optionalProduct.orElseThrow();
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());
        p.setSku(product.getSku());
        return Optional.of(productRepository.save(p));
    }

    @Override
    @Transactional
    public Optional<Product> delete(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        optionalProduct.ifPresent(p -> productRepository.delete(p));
        return optionalProduct;
    }

    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(p -> productRepository.delete(p));
        return optionalProduct;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existBySku(String sku) {
        return productRepository.existsBySku(sku);
    }
}
