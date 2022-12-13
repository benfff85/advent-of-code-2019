package com.adventofcode.year2022.day12;

import com.adventofcode.common.AdventOfCodeException;
import com.adventofcode.common.grid.PrintableGridElement;
import lombok.Data;

@Data
public class LandSquare implements PrintableGridElement {

    private final Integer level;
    private final String input;

    public LandSquare(String input) {

        level = switch (input) {
            case "a", "S" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            case "f" -> 5;
            case "g" -> 6;
            case "h" -> 7;
            case "i" -> 8;
            case "j" -> 9;
            case "k" -> 10;
            case "l" -> 11;
            case "m" -> 12;
            case "n" -> 13;
            case "o" -> 14;
            case "p" -> 15;
            case "q" -> 16;
            case "r" -> 17;
            case "s" -> 18;
            case "t" -> 19;
            case "u" -> 20;
            case "v" -> 21;
            case "w" -> 22;
            case "x" -> 23;
            case "y" -> 24;
            case "z", "E" -> 25;
            default -> throw new AdventOfCodeException("Invalid input!");
        };

        this.input = input;

    }

    @Override
    public String print() {
        return input;
    }
}
