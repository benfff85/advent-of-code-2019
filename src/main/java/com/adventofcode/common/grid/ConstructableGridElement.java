package com.adventofcode.common.grid;

import java.util.Arrays;

public interface ConstructableGridElement<T extends ConstructableGridElement<T>> extends PrintableGridElement {

    static <U extends ConstructableGridElement<U>> U createFromString(Class<U> elementType, String string) {
        return Arrays.stream(elementType.getEnumConstants())
                .filter(gridElement -> gridElement.print().equals(string))
                .findFirst()
                .orElseThrow();
    }

}
