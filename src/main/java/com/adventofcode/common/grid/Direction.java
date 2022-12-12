package com.adventofcode.common.grid;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public enum Direction {

    U(1), D(2), L(3), R(4);

    @Getter
    private final Integer value;

    Direction(Integer value) {
        this.value = value;
    }

    public static Direction getFromValue(Integer value) {
        Direction[] directions = Direction.values();
        for (Direction direction : directions) {
            if (direction.value.equals(value)) {
                return direction;
            }
        }
        return null;
    }

    public Direction opposite() {
        return switch (this) {
            case U -> D;
            case D -> U;
            case L -> R;
            case R -> L;
        };
    }

    public Direction cw90() {
        return switch (this) {
            case U -> R;
            case R -> D;
            case D -> L;
            case L -> U;
        };
    }

    public Direction ccw90() {
        return switch (this) {
            case U -> L;
            case L -> D;
            case D -> R;
            case R -> U;
        };
    }

    public static List<Direction> allCardinal() {
        return new ArrayList<>(List.of(U, D, L, R));
    }

}
