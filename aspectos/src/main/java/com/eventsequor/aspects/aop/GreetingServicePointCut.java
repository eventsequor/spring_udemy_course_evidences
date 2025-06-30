package com.eventsequor.aspects.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointCut {
    @Pointcut("execution(* com.eventsequor.aspects.services.GreetingServiceImpl.*(..))")
    public void greetingFooLoggerPointCut(){}

    @Pointcut("execution(* com.eventsequor.aspects.services.GreetingServiceImpl.*(..))")
    public void greetingLoggerPointCut(){}
}
