package com.adventofcode.year2023.day12;


import com.adventofcode.common.grid.PrintableGridElement;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SpringState implements PrintableGridElement {

    OPERATIONAL("."), BROKEN("#"), UNKNOWN("?");

    private final String string;

    SpringState(String string) {
        this.string = string;
    }

    public static SpringState fromString(String string) {
        return Arrays.stream(SpringState.values()).filter(springState -> springState.getString().equals(string)).findFirst().orElseThrow();
    }

    @Override
    public String print() {
        return string;
    }

}
