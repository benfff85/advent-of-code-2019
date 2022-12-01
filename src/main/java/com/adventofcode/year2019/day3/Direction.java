package com.adventofcode.year2019.day3;


import lombok.Getter;

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

}
