package com.eventsequor.invoice.configurations;

import com.eventsequor.invoice.models.Item;
import com.eventsequor.invoice.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource(value = "classpath:invoice.properties", encoding = "UTF-8")
public class InvoiceConfig {

    @Bean
    public List<Item> getItemInvoice() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(new Product(800, "Bicycle"), 1));
        items.add(new Item(new Product(25, "Wheel"), 2));
        items.add(new Item(new Product(250, "Brake system"), 1));
        return items;
    }

    @Bean
    @Primary
    public List<Item> getOfficeItemInvoice() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(new Product(800, "Computer Mac m2"), 1));
        items.add(new Item(new Product(25, "Mouse logitech"), 2));
        items.add(new Item(new Product(250, "Monitor"), 1));
        items.add(new Item(new Product(30, "Keyboard"), 2));
        items.add(new Item(new Product(220, "Board"), 3));

        return items;
    }
}
