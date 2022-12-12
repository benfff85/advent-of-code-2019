package com.adventofcode.year2019.day18;


import com.adventofcode.common.grid.PrintableGridElement;
import lombok.Getter;

public enum GridElement implements PrintableGridElement {

    WALL(0, "#"), EMPTY(1, ".");

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

    public static GridElement getFromPrintableValue(String string) {
        GridElement[] gridElements = GridElement.values();
        for (GridElement gridElement : gridElements) {
            if (gridElement.string.equals(string)) {
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
