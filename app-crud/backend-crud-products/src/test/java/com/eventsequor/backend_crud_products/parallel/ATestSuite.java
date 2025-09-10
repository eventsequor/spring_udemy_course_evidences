package com.eventsequor.backend_crud_products.parallel;


import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ATestSuite implements ParallerLeandro {
    @Override
    public Stream<Arguments> myArguments() {
        return Stream.of(Arguments.of("A", 1));
    }


}
