package com.eder.spring_web.controllers;

import com.eder.spring_web.dto.ParamDto;
import com.eder.spring_web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/var")
public class PathValueController {

    @Value("${config.username}")
    private String username;

    //@Value("${config.message}")
    //private String message;

    @Value("${config.listOfValues}")
    private List<String> listOfValues;

    @Value("${config.code}")
    private int code;

    @Value("#{'${config.listOfValues}'.split(',')}")
    private List<String> values;

    @Value("#{'${config.listOfValues}'}")
    private String valuesString;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.product}")
    private String product;

    @Value("#{${config.valuesMap}.price}")
    private String price;

    @Autowired
    private Environment environment;

    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable(required = false) String message) {
        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message);
        return paramDto;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVariable(@PathVariable String product, @PathVariable String id) {
        Map<String, Object> json = new HashMap<>();
        json.put("id", id);
        json.put("product", product);
        return json;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        System.out.println("User created: " + user);
        return user;
    }

    @GetMapping("/values")
    public Map<String, Object> value(@Value("${config.message}") String message) {
        Map<String, Object> json = new HashMap<>();
        json.put("username", username);
        json.put("code", code);
        json.put("message", message);
        json.put("listOfValues", listOfValues);
        json.put("values", values);
        json.put("valuesString", valuesString);
        json.put("valuesMap", valuesMap);
        json.put("product", product);
        json.put("price", price);
        json.put("messageEnv", environment.getProperty("config.message"));
        return json;
    }
}
