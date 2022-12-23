package com.adventofcode.year2022.day15;


import com.adventofcode.common.grid.PrintableGridElement;
import lombok.Getter;

public enum GridElement implements PrintableGridElement {

    COVERED("#"), BEACON("B"), SENSOR("S");

    @Getter
    private final String string;

    GridElement(String string) {
        this.string = string;
    }

    @Override
    public String print() {
        return string;
    }

}
