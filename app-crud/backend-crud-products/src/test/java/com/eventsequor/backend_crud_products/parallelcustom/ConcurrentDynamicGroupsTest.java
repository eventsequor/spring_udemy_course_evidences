package com.eventsequor.backend_crud_products.parallelcustom;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConcurrentDynamicGroupsTest {

    private final ExecutorService executor = Executors.newFixedThreadPool(2); // N grupos paralelos

    @TestFactory
    Stream<DynamicTest> dynamicTestsInParallelGroups() throws InterruptedException {
        List<String[]> groups = List.of(
                new String[]{"ClaseName1", "E", "F"},
                new String[]{"ClaseName2", "G", "H"},
                new String[]{"ClaseName3", "I", "J"}
        );

        List<Future<List<DynamicTest>>> futures = groups.stream()
                .map(group -> executor.submit(() -> buildTests(group[0], group[1], group[2])))
                .collect(Collectors.toList());

        // Recoger resultados
        return futures.stream().flatMap(future -> {
            try {
                return future.get().stream(); // Espera a que termine cada grupo
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<DynamicTest> buildTests(String groupName, String... params) {
        System.out.println("▶ Group started: " + groupName + " on thread " + Thread.currentThread().getName());
        return Stream.of(params)
                .map(input -> DynamicTest.dynamicTest(groupName + " -> input: " + input, () -> {
                    System.out.printf("   ▶ %s - running input: %s on thread: %s%n", groupName, input, Thread.currentThread().getName());
                    Thread.sleep(1000); // Simula trabajo
                }))
                .collect(Collectors.toList());
    }
}
