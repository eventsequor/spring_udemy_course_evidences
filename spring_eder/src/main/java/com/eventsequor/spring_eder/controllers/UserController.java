package com.eventsequor.spring_eder.controllers;

import com.eventsequor.spring_eder.models.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private List<Visitor> visitors;

    @GetMapping("/visitors")
    public List<Visitor> getVisitors() {
        return visitors;
    }

    @PostMapping("/append")
    public List<Visitor> createVisitor(@RequestBody Visitor visitor) {
        if (visitors.stream().noneMatch(v -> v.getId().equalsIgnoreCase(visitor.getId())))
            visitors.add(visitor);
        return visitors;
    }

    @PostMapping("/delete")
    public List<Visitor> deleteVisitor(@RequestBody Visitor visitor) {
        visitors.stream().filter(v -> v.getId().equalsIgnoreCase(visitor.getId())).findFirst().ifPresent(visitorFound -> visitors.remove(visitorFound));
        return visitors;
    }
}
