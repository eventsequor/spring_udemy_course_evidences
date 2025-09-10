package com.eventsequor.backend_crud_products.parallelcustom;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DynamicTestLauncher {

    @TestFactory
    Stream<DynamicNode> dynamicGroups() {
        return Stream.of(
                buildGroup("ClaseName1", "E", "Fa"),
                buildGroup("ClaseName2", "G", "H")
        );
    }

    private DynamicContainer buildGroup(String groupName, String... params) {
        List<DynamicTest> tests = Stream.of(params)
                .map(input -> DynamicTest.dynamicTest("input: " + input, () -> {
                    // Aquí puedes invocar tu test real o lógica equivalente
                    System.out.printf("▶ Running input: %s on thread: %s%n", input, Thread.currentThread().getName());
                    Thread.sleep(500);
                }))
                .collect(Collectors.toList());

        return DynamicContainer.dynamicContainer(groupName, tests);
    }
}

