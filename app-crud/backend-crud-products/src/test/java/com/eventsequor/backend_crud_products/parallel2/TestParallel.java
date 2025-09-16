package com.eventsequor.backend_crud_products.parallel2;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
public class TestParallel {

    public void genericTest(String text) throws InterruptedException {
        System.out.println("Starting test: " + text);
        Thread.sleep(3000);
        System.out.println();
        System.out.println("This test is with: " + text);
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class Test1 {

        @ParameterizedTest(name = "this test is with {0}", allowZeroInvocations = true)
        @MethodSource("hollywoodSources")
        @Execution(ExecutionMode.CONCURRENT)
        public void hollywood(String text) throws InterruptedException {
            genericTest(text);
        }

        public Stream<Arguments> hollywoodSources() {
            return Stream.of("hollywood_1", "hollywood_2", "hollywood_3").map(Arguments::of);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class Test2 {
        @ParameterizedTest(name = "this test is with {0}", allowZeroInvocations = true)
        @MethodSource("montereySources")
        @Execution(ExecutionMode.SAME_THREAD)
        public void monterey(String text) throws InterruptedException {
            genericTest(text);
        }

        public Stream<Arguments> montereySources() {
            return Stream.of("monterey_1", "monterey_2", "monterey_3").map(Arguments::of);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class Test3 {
        @ParameterizedTest(name = "this test is with {0}", allowZeroInvocations = true)
        @MethodSource("pantherSources")
        @Execution(ExecutionMode.SAME_THREAD)
        public void panther(String text) throws InterruptedException {
            genericTest(text);
        }

        public Stream<Arguments> pantherSources() {
            return Stream.of("panther_1", "panther_2", "panther_3").map(Arguments::of);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class Test4 {
        @ParameterizedTest(name = "this test is with {0}", allowZeroInvocations = true)
        @MethodSource("seacliffSources")
        @Execution(ExecutionMode.SAME_THREAD)
        public void seacliff(String text) throws InterruptedException {
            genericTest(text);
        }

        public Stream<Arguments> seacliffSources() {
            return Stream.of("hola").map(Arguments::of);
        }
    }
}
