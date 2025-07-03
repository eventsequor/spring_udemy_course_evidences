package com.eventsequor.crud_jpa.validations;

import com.eventsequor.crud_jpa.services.IProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IsExistDbValidator implements ConstraintValidator<IsExistDb, String> {

    @Autowired
    private IProductService productService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !productService.existBySku(s);
    }
}
