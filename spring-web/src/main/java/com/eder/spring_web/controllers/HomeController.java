package com.eder.spring_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"","/","/home"})
    public String home(){
        return "forward:/details-list";
    }

}
