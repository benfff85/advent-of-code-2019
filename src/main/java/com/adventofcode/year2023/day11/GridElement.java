package com.adventofcode.year2023.day11;


import com.adventofcode.common.grid.PrintableGridElement;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GridElement implements PrintableGridElement {

    EMPTY("."), GALAXY("#");

    private final String string;

    GridElement(String string) {
        this.string = string;
    }

    public static GridElement fromString(String string) {
        return Arrays.stream(GridElement.values()).filter(gridElement -> gridElement.getString().equals(string)).findFirst().orElseThrow();
    }

    @Override
    public String print() {
        return string;
    }

}
