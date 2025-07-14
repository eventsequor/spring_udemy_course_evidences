package com.eventsequor.crud_jpa.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsByUserValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUserName {

    String message() default "the user is already exist in the data base or should not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
