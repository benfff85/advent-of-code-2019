package com.adventofcode.year2023.day16;


import com.adventofcode.common.grid.ConstructableGridElement;
import lombok.Getter;

@Getter
public enum GridElement implements ConstructableGridElement<GridElement> {

    EMPTY("."), SPLITTER1("|"), SPLITTER2("-"), SHIFT1("/"), SHIFT2("\\"), COVERED("#");

    private final String string;

    GridElement(String string) {
        this.string = string;
    }

    @Override
    public String print() {
        return string;
    }

}
