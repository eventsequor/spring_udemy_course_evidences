package com.eventsequor.backend_crud_products.parallel;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface ParallerLeandro {

    @GroupedParameterizedTest
    @MethodSource("myArguments")
    @Execution(ExecutionMode.CONCURRENT)
    default void myTest(String label, int value) throws InterruptedException {
        System.out.println("Running: " + label + " on " + Thread.currentThread().getName());
        Thread.sleep(value * 1000);
    }

    Stream<Arguments> myArguments();
}
