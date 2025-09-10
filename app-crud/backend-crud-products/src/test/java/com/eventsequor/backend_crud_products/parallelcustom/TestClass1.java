package com.eventsequor.backend_crud_products.parallelcustom;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

@Execution(ExecutionMode.CONCURRENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestClass1 {

    @ParameterizedTest
    @MethodSource("sourceParameter")
    @Execution(ExecutionMode.CONCURRENT)
    void testA(String input) throws InterruptedException {
        System.out.println("▶ Running input: " + input + " on thread: " + Thread.currentThread().getName());
        System.out.println("input: " + input);
        if(input.equalsIgnoreCase("E"))
            Assertions.fail();
        Thread.sleep(3000);
    }

    Stream<Arguments> sourceParameter() {
        return Stream.of("A", "B").map(Arguments::of);
    }


    @BeforeEach
    public void each() throws InterruptedException {
        System.out.println("Before is ready");
    }
}


