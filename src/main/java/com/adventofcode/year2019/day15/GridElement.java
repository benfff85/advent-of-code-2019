package com.adventofcode.year2019.day15;


import com.adventofcode.common.grid.PrintableGridElement;
import lombok.Getter;

public enum GridElement implements PrintableGridElement {

    WALL(0, "#"), EMPTY(1, "."), OXYGEN(2, "*");

    @Getter
    private final Integer value;

    @Getter
    private final String string;

    GridElement(Integer value, String string) {
        this.value = value;
        this.string = string;
    }

    public static GridElement getFromValue(Integer value) {
        GridElement[] gridElements = GridElement.values();
        for (GridElement gridElement : gridElements) {
            if (gridElement.value.equals(value)) {
                return gridElement;
            }
        }
        return null;
    }

    @Override
    public String print() {
        return string;
    }

}
