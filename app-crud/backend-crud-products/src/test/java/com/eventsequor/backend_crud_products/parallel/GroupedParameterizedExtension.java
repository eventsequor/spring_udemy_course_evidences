package com.eventsequor.backend_crud_products.parallel;

import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupedParameterizedExtension implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return context.getTestMethod().isPresent() &&
                context.getTestMethod().get().isAnnotationPresent(GroupedParameterizedTest.class);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        Method testMethod = context.getRequiredTestMethod();
        MethodSource methodSource = testMethod.getAnnotation(MethodSource.class);

        String methodSourceName = methodSource.value()[0];
        Class<?> testClass = context.getRequiredTestClass();

        Method sourceMethod = Arrays.stream(testClass.getDeclaredMethods())
                .filter(m -> m.getName().equals(methodSourceName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such method source: " + methodSourceName));

        Object result;
        try {
            result = Modifier.isStatic(sourceMethod.getModifiers()) ?
                    sourceMethod.invoke(null) : sourceMethod.invoke(testClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Error invoking method source", e);
        }

        @SuppressWarnings("unchecked")
        Stream<Arguments> argumentsStream = (Stream<Arguments>) result;
        List<Arguments> arguments = argumentsStream.toList();

        // Agrupar por lógica (pares e impares)
        Map<String, List<Arguments>> grouped = arguments.stream()
                .collect(Collectors.groupingBy(arg -> {
                    int value = (int) arg.get()[1];
                    return value % 2 == 0 ? "even" : "odd";
                }));

        // Crear contextos
        return grouped.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream().map(args -> new TestTemplateInvocationContext() {
                    @Override
                    public String getDisplayName(int invocationIndex) {
                        return "Group " + entry.getKey() + " -> " + Arrays.toString(args.get());
                    }

                    @Override
                    public List<Extension> getAdditionalExtensions() {
                        return List.of(new ParameterResolver() {
                            @Override
                            public boolean supportsParameter(ParameterContext pc, ExtensionContext ec) {
                                return true;
                            }

                            @Override
                            public Object resolveParameter(ParameterContext pc, ExtensionContext ec) {
                                return args.get()[pc.getIndex()];
                            }
                        });
                    }
                }));
    }

}