package com.eder.spring_web.controllers;

import com.eder.spring_web.dto.UserDto;
import com.eder.spring_web.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController2 {

    @GetMapping("/details")
    public Map<String, Object> detailsUsingMap(){
        User user = new User("Eder", "Carbonero");
        Map<String, Object> body = new HashMap<>();
        body.put("title","Hello world!");
        body.put("user", user);
        return body;
    }

    @GetMapping(path = "/details-map")
    public UserDto detailsUsingDto(){
        UserDto userDto = new UserDto();
        userDto.setTitle("Title of example");
        userDto.setUser(new User("Eder", "Carbonero"));
        return userDto;
    }

    @GetMapping(path = "/details-list")
    public List<UserDto> detailsList(){
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto();
        userDto.setTitle("Title of example");
        userDto.setUser(new User("Eder", "Carbonero","email@email.com"));

        userDtoList.add(userDto);
        userDtoList.add(userDto);

        return userDtoList;
    }
}