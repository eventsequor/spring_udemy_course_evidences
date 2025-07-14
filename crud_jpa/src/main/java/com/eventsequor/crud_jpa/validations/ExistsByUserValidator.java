package com.eventsequor.crud_jpa.validations;

import com.eventsequor.crud_jpa.services.IUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUserValidator implements ConstraintValidator<ExistsByUserName, String> {

    @Autowired
    private IUserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (userService == null)
            return true;
        return !userService.existsByUsername(s);
    }
}
