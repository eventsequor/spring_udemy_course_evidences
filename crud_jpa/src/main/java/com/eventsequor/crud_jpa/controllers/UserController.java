package com.eventsequor.crud_jpa.controllers;

import com.eventsequor.crud_jpa.entities.User;
import com.eventsequor.crud_jpa.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(originPatterns = "*", origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public List<User> list() {
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors())
            return validation(bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        user.setAdmin(false);
        return create(user, bindingResult);
    }

    private ResponseEntity<?> validation(BindingResult bindingResult) {
        Map<String, String> errorsMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(errors -> {
            errorsMap.put(errors.getField(), "The field " + errors.getField() + " - " + errors.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errorsMap);
    }
}
