package com.eventsequor.backend_crud_products.parallelcustom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelGroupTest {

    @TestFactory
    Stream<DynamicContainer> testPorGrupos() {
        Map<String, List<String>> grupos = Map.of(
                "Grupo 1", List.of("A", "B"),
                "Grupo 2", List.of("C", "D"),
                "Grupo 3", List.of("E", "F")
        );

        return grupos.entrySet().stream().map(entry -> {
            String grupoNombre = entry.getKey();
            List<String> inputs = entry.getValue();

            List<DynamicTest> tests = inputs.stream()
                    .map(input -> DynamicTest.dynamicTest("Test input: " + input, () -> testA(input)))
                    .collect(Collectors.toList());

            return DynamicContainer.dynamicContainer(grupoNombre, tests);
        });
    }

    @Execution(ExecutionMode.CONCURRENT)
    void testA(String input) throws InterruptedException {
        System.out.println("▶ Running input: " + input + " on thread: " + Thread.currentThread().getName());
        System.out.println("input: " + input);
        if (input.equalsIgnoreCase("E"))
            Assertions.fail();
        Thread.sleep(3000);
    }

    @TestFactory
    Stream<DynamicContainer> grupos() {
        return Stream.of(
                DynamicContainer.dynamicContainer("Grupo 1", Stream.of("A", "B")
                        .map(nombre -> DynamicTest.dynamicTest("Test " + nombre, () -> {
                            System.out.println("Ejecutando " + nombre + " en hilo: " + Thread.currentThread().getName());
                            Thread.sleep(1000);
                        }))
                ),
                DynamicContainer.dynamicContainer("Grupo 2", Stream.of("C", "D")
                        .map(nombre -> DynamicTest.dynamicTest("Test " + nombre, () -> {
                            System.out.println("Ejecutando " + nombre + " en hilo: " + Thread.currentThread().getName());
                            Thread.sleep(1000);
                        }))
                )
        );
    }
}

