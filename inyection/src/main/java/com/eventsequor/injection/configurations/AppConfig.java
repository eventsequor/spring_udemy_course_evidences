package com.eventsequor.injection.configurations;

import com.eventsequor.injection.repositories.IProductRepository;
import com.eventsequor.injection.repositories.ProductRepositoryJson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    @SessionScope
    public IProductRepository getProductRepositoryJson() throws IOException {
        return new ProductRepositoryJson();
    }
}
