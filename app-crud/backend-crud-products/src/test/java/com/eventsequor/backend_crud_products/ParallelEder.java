package com.eventsequor.backend_crud_products;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class ParallelEder {

    @Test
    void customTestExecutionQueue() throws InterruptedException {
        Queue<Runnable> testQueue = new LinkedList<>();

        // Test 1
        testQueue.add(() -> {
            System.out.println("Running test A");
            sleep(1000);
        });

        // Test 2
        testQueue.add(() -> {
            System.out.println("Running test B");
            sleep(1000);
        });

        // Test 3
        testQueue.add(() -> {
            System.out.println("Running test C");
            sleep(1000);
        });

        // Simular ejecución controlada en paralelo
        ExecutorService executor = Executors.newFixedThreadPool(2); // Máximo 2 en paralelo

        while (!testQueue.isEmpty()) {
            Runnable task = testQueue.poll();
            executor.submit(task); // Tú decides cuándo lanzar cada uno
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @TestFactory
    @Execution(ExecutionMode.CONCURRENT)
    Collection<DynamicTest> testQueueAsDynamicTests() {
        List<DynamicTest> tests = new ArrayList<>();

        tests.add(DynamicTest.dynamicTest("Test A", () -> {
            System.out.println("Running Test A");
            sleep(3000);
        }));

        tests.add(DynamicTest.dynamicTest("Test B", () -> {
            System.out.println("Running Test B");
            sleep(1000);
        }));

        tests.add(DynamicTest.dynamicTest("Test C", () -> {
            System.out.println("Running Test C");
            sleep(1000);
        }));

        // Podrías incluso crear una cola aquí y controlarlo manualmente
        return tests;
    }

    @TestFactory
    Collection<DynamicTest> groupedDynamicTests() {
        List<DynamicTest> group1 = List.of(
                DynamicTest.dynamicTest("[Group 1] Test A", () -> {
                    System.out.println("Running A");
                    sleep(4000);
                }),
                DynamicTest.dynamicTest("[Group 1] Test B", () -> System.out.println("Running B"))
        );

        List<DynamicTest> group2 = List.of(
                DynamicTest.dynamicTest("[Group 2] Test C", () -> System.out.println("Running C")),
                DynamicTest.dynamicTest("[Group 2] Test D", () -> System.out.println("Running D"))
        );

        // Aplanas la lista manualmente
        List<DynamicTest> allTests = new ArrayList<>();
        allTests.addAll(group1);
        allTests.addAll(group2);
        return allTests;
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsWithManualThreads() throws InterruptedException {
        int testCount = 5;

        ExecutorService executor = Executors.newFixedThreadPool(testCount); // o usar ForkJoinPool.commonPool()

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        List<DynamicTest> tests = new ArrayList<>();

        for (int i = 1; i <= testCount; i++) {
            int finalI = i;
            DynamicTest test = DynamicTest.dynamicTest("Test " + finalI, () -> {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        System.out.printf("▶ Test %d running in thread: %s%n",
                                finalI, Thread.currentThread().getName());
                        Thread.sleep(1000 + (long) (Math.random() * 1000));
                        if (Math.random() > 0.7) {
                            throw new RuntimeException("💥 Simulated failure in test " + finalI);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Interrupted", e);
                    }
                }, executor);

                futures.add(future); // almacenamos la tarea para esperar al final
            });

            tests.add(test);
        }

        // Devuelve los tests que se lanzan y completan por fuera del cuerpo del test
        // Necesitamos esperar todos después de haberlos lanzado, en un hook externo o al final del test factory
        DynamicTest waitForCompletion = DynamicTest.dynamicTest("Wait for all tests to complete", () -> {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            executor.shutdown();
        });

        tests.add(waitForCompletion);

        return tests;
    }


    @ParameterizedTest
    @MethodSource("parallelSource")
    void testInParallel(String name, int value) throws InterruptedException {
        System.out.println("▶ " + name + " (" + value + ") in thread " + Thread.currentThread().getName());
        Thread.sleep(1000);
    }

    static Stream<Arguments> parallelSource() {
        return Stream.of(
                Arguments.of("Even", 2),
                Arguments.of("Odd", 3),
                Arguments.of("Even", 4),
                Arguments.of("Odd", 5)
        );
    }


    @TestFactory
    Collection<DynamicTest> groupedParallelTests() throws InterruptedException {
        // Simulamos datos tipo MethodSource
        List<Arguments> allArgs = List.of(
                Arguments.of("param1", 1),
                Arguments.of("param2", 2),
                Arguments.of("param3", 3),
                Arguments.of("param4", 4)
        );

        // Agrupamos por paridad
        Map<String, List<Arguments>> grouped = allArgs.stream().collect(
                Collectors.groupingBy(arg -> ((int) arg.get()[1]) % 2 == 0 ? "even" : "odd")
        );

        // Executor para los grupos
        ExecutorService executor = Executors.newFixedThreadPool(grouped.size());

        List<DynamicTest> resultTests = new ArrayList<>();
        List<Future<List<DynamicTest>>> futures = new ArrayList<>();

        for (Map.Entry<String, List<Arguments>> groupEntry : grouped.entrySet()) {
            String groupName = groupEntry.getKey();
            List<Arguments> argsList = groupEntry.getValue();

            // Cada grupo se ejecuta en paralelo
            Future<List<DynamicTest>> future = executor.submit(() -> {
                List<DynamicTest> groupTests = new ArrayList<>();

                for (Arguments args : argsList) {
                    String label = (String) args.get()[0];
                    int value = (int) args.get()[1];

                    groupTests.add(dynamicTest("Group: " + groupName + " - " + label, () -> {
                        System.out.printf("✅ Test %s (value=%d) running in thread: %s%n",
                                label, value, Thread.currentThread().getName());
                        Thread.sleep(5000); // Simulamos trabajo
                    }));
                }

                return groupTests;
            });

            futures.add(future);
        }

        // Esperamos resultados y recopilamos tests
        for (Future<List<DynamicTest>> future : futures) {
            try {
                resultTests.addAll(future.get());
            } catch (ExecutionException e) {
                throw new RuntimeException("Error in test execution", e.getCause());
            }
        }

        executor.shutdown();
        return resultTests;
    }

    @TestFactory
    @Execution(ExecutionMode.SAME_THREAD)
    Stream<DynamicNode> groupedTests() {
        return Stream.of(
                dynamicContainer("Grupo 1", Stream.of(
                        dynamicTest("param1", () -> {
                            sleep(4000);
                        }),
                        dynamicTest("param3", () -> {
                            sleep(4000);
                        })
                )),
                dynamicContainer("Grupo 2", Stream.of(
                        dynamicTest("param2", () -> {
                            sleep(4000);
                        }),
                        dynamicTest("param4", () -> {
                            sleep(4000);
                        })
                ))
        );
    }

    @TestFactory
    Stream<DynamicContainer> groupedTests2() {
        return Stream.of(
                DynamicContainer.dynamicContainer("Grupo 1", Stream.of("A", "B")
                        .map(name -> DynamicTest.dynamicTest("Test " + name, () -> {
                            System.out.println("Running " + name + " on thread: " + Thread.currentThread().getName());
                            Thread.sleep(1000); // Simula trabajo
                        }))
                ),
                DynamicContainer.dynamicContainer("Grupo 2", Stream.of("C", "D")
                        .map(name -> DynamicTest.dynamicTest("Test " + name, () -> {
                            System.out.println("Running " + name + " on thread: " + Thread.currentThread().getName());
                            Thread.sleep(1000);
                        }))
                )
        );
    }

}
