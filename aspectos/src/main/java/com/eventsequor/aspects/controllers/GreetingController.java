package com.eventsequor.aspects.controllers;

import com.eventsequor.aspects.services.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class GreetingController {

    @Autowired
    private IGreetingService greetingService;
    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(Collections.singletonMap("greeting", greetingService.sayHello("Pepe","Hello, how are you?")));
    }


    @GetMapping("/greeting_error")
    public ResponseEntity<?> greetingError() {
        return ResponseEntity.ok(Collections.singletonMap("greeting", greetingService.sayHelloError("Pepe","Hello, how are you?")));
    }
}
