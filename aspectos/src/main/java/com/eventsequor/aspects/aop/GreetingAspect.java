package com.eventsequor.aspects.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private final Logger logger = LoggerFactory.getLogger(GreetingAspect.class);

    @Before("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("before method: {} - with arguments {}", method, args);
    }

    @After("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("after method: {} - with arguments {}", method, args);
    }

    @AfterReturning("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerAfterReturning(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("after returning method: {} - with arguments {}", method, args);
    }

    @AfterThrowing("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerAfterThrowing(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("after launch the exception method: {} - with arguments {}", method, args);
    }

    @Around("GreetingServicePointCut.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result;
        try {
            logger.info("This is similar to before: ");
            result = joinPoint.proceed();
            logger.info("This is similar to after: ");
            return result;
        } catch (Throwable e) {
            logger.error("This is similar to catch the exception");
            throw e;
        }
    }
}
