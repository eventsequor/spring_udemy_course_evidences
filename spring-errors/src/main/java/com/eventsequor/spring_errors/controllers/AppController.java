package com.eventsequor.spring_errors.controllers;

import com.eventsequor.spring_errors.exceptions.UserNotFoundException;
import com.eventsequor.spring_errors.models.domain.User;
import com.eventsequor.spring_errors.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    IUserService userService;

    @GetMapping("/index1")
    public String index() {
        int value = 100 / 0;
        return "200 ok";
    }

    @GetMapping("/index2")
    public String index2() {
        int value = Integer.parseInt("10x");
        return "200 ok";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable long id) {
        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("The user with id <%s> were not found", id)));
    }

    @GetMapping("/show2/{id}")
    public ResponseEntity<?> show2(@PathVariable long id) {
        Optional<User> userOptional = userService.findById(id);
        if(userOptional.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userOptional.orElseThrow());
    }
}
