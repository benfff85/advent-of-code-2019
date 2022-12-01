package com.adventofcode.year2019.day11;

import lombok.Getter;

public enum Color {

    BLACK(0, " "), WHITE(1, "X");

    @Getter
    private final Integer value;

    @Getter
    private final String string;

    Color(Integer value, String string) {
        this.value = value;
        this.string = string;
    }

    public static Color getFromValue(Integer value) {
        Color[] colors = Color.values();
        for (Color color : colors) {
            if (color.value.equals(value)) {
                return color;
            }
        }
        return null;
    }

    public String print() {
        return String.valueOf(string);
    }

}
