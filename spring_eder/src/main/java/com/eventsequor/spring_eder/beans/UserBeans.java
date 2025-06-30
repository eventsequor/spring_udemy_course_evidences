package com.eventsequor.spring_eder.beans;

import com.eventsequor.spring_eder.models.Visitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserBeans {

    @Bean
    public List<Visitor> getVisitors(){
        return new ArrayList<>();
    }
}
