package com.eder.spring_web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRestController {

    @GetMapping("/details2")
    public Map<String, Object> details(){
        Map<String, Object> body = new HashMap<>();
        body.put("title","Hello world!");
        body.put("name", "Eder");
        body.put("lastName", "Carbonero");
        return body;
    }
}
