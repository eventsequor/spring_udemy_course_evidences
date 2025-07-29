package com.eventsequor.crud_jpa.controllers;

import com.eventsequor.crud_jpa.validations.ProductValidator;
import com.eventsequor.crud_jpa.entities.Product;
import com.eventsequor.crud_jpa.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent())
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        Optional<Product> optionalProduct = productService.update(id, product);
        return optionalProduct
                .map(value -> ResponseEntity.status(HttpStatus.CREATED).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> optionalProduct = productService.delete(id);
        if (optionalProduct.isPresent())
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult bindingResult) {
        Map<String, String> errorsMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(errors -> {
            errorsMap.put(errors.getField(), "The field " + errors.getField() + " - " + errors.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errorsMap);
    }
}
