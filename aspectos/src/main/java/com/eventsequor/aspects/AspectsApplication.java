package com.eventsequor.aspects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class AspectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspectsApplication.class, args);
	}
}
