package com.eventsequor.spring_errors;

import com.eventsequor.spring_errors.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {


    @Bean("users")
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Eder", "Carbonero"));
        users.add(new User(2L, "Andres", "Cortes"));
        users.add(new User(3L, "John", "Agudelo"));
        users.add(new User(4L, "Raul", "Gonzales"));
        users.add(new User(4L, "Angel", "Dimaria"));
        return users;
    }
}
