package com.eventsequor.relations_jpa;

import com.eventsequor.relations_jpa.services.InvoiceClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RelationsJpaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RelationsJpaApplication.class, args);
    }

    @Autowired
    private InvoiceClientService invoiceClientService;

    @Override
    public void run(String... args) {
        invoiceClientService.manyToManyRemoveBidirectionalFind();
    }
}
