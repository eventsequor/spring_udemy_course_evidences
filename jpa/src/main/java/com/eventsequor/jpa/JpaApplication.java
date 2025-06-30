package com.eventsequor.jpa;

import com.eventsequor.jpa.repositories.IPersonRepository;
import com.eventsequor.jpa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        personService.update();
    }
}
