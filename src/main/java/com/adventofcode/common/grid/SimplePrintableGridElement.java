package com.adventofcode.common.grid;

import lombok.Data;

@Data
public class SimplePrintableGridElement implements PrintableGridElement {

    private final String string;

    private SimplePrintableGridElement(String string) {
        this.string = string;
    }

    public static SimplePrintableGridElement of(String string) {
        return new SimplePrintableGridElement(string);
    }

    public String print() {
        return string;
    }

}
