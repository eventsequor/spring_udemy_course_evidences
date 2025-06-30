package com.eder.spring_web.controllers;

import com.eder.spring_web.dto.ParamDto;
import com.eder.spring_web.dto.ParamDto2;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/params")
public class RequestParamController {

    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false) String message) {
        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message);
        return paramDto;
    }

    @GetMapping("/bar")
    public ParamDto bar(@RequestParam String text, @RequestParam Integer code) {
        ParamDto2 paramDto = new ParamDto2();
        paramDto.setMessage(text);
        paramDto.setCode(code);
        return paramDto;
    }

    @GetMapping("/request")
    public ParamDto2 request(HttpServletRequest request) {
        int code;
        try {
            code = Integer.parseInt(request.getParameter("code"));
        } catch (NumberFormatException e) {
            code = 0;
        }
        ParamDto2 paramDto2 = new ParamDto2();
        paramDto2.setCode(code);
        paramDto2.setMessage(request.getParameter("message"));
        return paramDto2;
    }
}
