package com.eder.spring_web.controllers;

import com.eder.spring_web.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {
        User user = new User("Eder", "Carbonero");
        model.addAttribute("title", "hello world!");
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/details-list")
    public String detailsList(ModelMap model) {
        model.addAttribute("title", "list users");
        return "details-list";
    }

    @ModelAttribute("users")
    public List<User> userModel() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Eder", "Carbonero", "eder@email.com"));
        userList.add(new User("Elizabeth", "Suarez", "elizabeth@email.com"));
        userList.add(new User("Lucia", "Baquero", "lucia@email.com"));
        userList.add(new User("Roberto", "Diaz"));
        return userList;
    }
}
