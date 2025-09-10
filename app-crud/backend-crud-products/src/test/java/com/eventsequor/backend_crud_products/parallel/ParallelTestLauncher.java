package com.eventsequor.backend_crud_products.parallel;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.*;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ParallelTestLauncher {

    public static void main(String[] args) throws InterruptedException {
        // ExecutorService con 2 hilos (puedes ajustar el número)
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Clases de prueba que quieres ejecutar
        List<Class<?>> testClasses = List.of(TestClass1.class, TestClass2.class);

        for (Class<?> testClass : testClasses) {
            executor.submit(() -> runTestClass(testClass));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
    }

    private static void runTestClass(Class<?> testClass) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(testClass))
                .build();

        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        // Mostrar resultados
        TestExecutionSummary summary = listener.getSummary();
        System.out.println("Resultados para " + testClass.getSimpleName() + ":");
    }

    Class<? extends TestClass1> generatedTestClass = new ByteBuddy()
            .subclass(TestClass1.class)
            .name("my.generated.GeneratedTestClass1")
            .method(named("sourceParameter"))
            .intercept(MethodDelegation.to(new Object() {
                public Stream<Arguments> sourceParameter() {
                    return Stream.of("C", "D").map(Arguments::of);
                }
            }))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class TestClass1 {
        @ParameterizedTest
        @MethodSource("sourceParameter")
        void testA(String input) throws InterruptedException {
            System.out.println("▶ TestClass1 - testA: " + Thread.currentThread().getName());
            System.out.println("Input: " + input);
            Thread.sleep(1000);
        }

        Stream<Arguments> sourceParameter() {
            return Stream.of("A", "B").map(Arguments::of);
        }
    }

    @Nested
    class TestClass2 {
        @org.junit.jupiter.api.Test
        void testB() throws InterruptedException {
            System.out.println("▶ TestClass2 - testB: " + Thread.currentThread().getName());
            Thread.sleep(1000);
        }
    }
}


