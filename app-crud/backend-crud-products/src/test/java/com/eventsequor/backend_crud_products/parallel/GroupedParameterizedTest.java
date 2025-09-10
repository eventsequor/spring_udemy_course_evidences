package com.eventsequor.backend_crud_products.parallel;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(GroupedParameterizedExtension.class)
@TestTemplate
public @interface GroupedParameterizedTest {
    String name() default "{index} => {arguments}";
    String groupBy() default ""; // nombre del método o expresión
}
