package com.adventofcode.year2022.day14;


import com.adventofcode.common.grid.PrintableGridElement;
import lombok.Getter;

public enum GridElement implements PrintableGridElement {

    ROCK("#"), AIR("."), SOURCE("+"), SAND("O");

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
