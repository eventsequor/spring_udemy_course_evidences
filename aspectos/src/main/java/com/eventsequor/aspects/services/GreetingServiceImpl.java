package com.eventsequor.aspects.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements IGreetingService {

    public final Logger logger = LoggerFactory.getLogger(GreetingServiceImpl.class);

    @Override
    public String sayHello(String person, String phrase) {
        String greeting = person + " " + phrase;
        logger.info("Greeting: {}", greeting);
        return greeting;
    }

    @Override
    public String sayHelloError(String person, String phrase) {
        throw new RuntimeException("Some error");
    }
}
