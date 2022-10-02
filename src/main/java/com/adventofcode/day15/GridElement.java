package com.adventofcode.day15;


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
        GridElement[] directions = GridElement.values();
        for (GridElement direction : directions) {
            if (direction.value.equals(value)) {
                return direction;
            }
        }
        return null;
    }

    @Override
    public String print() {
        return string;
    }

}
