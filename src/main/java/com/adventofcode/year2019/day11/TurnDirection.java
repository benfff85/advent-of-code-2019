package com.adventofcode.year2019.day11;

import lombok.Getter;

public enum TurnDirection {
    CW90(1), CCW90(0);

    @Getter
    private final Integer value;

    TurnDirection(Integer value) {
        this.value = value;
    }

    public static TurnDirection getFromValue(Integer value) {
        TurnDirection[] turnDirections = TurnDirection.values();
        for (TurnDirection turnDirection : turnDirections) {
            if (turnDirection.value.equals(value)) {
                return turnDirection;
            }
        }
        return null;
    }

}
