package com.adventofcode.year2024.day3;

import lombok.Getter;

@Getter
public class MultiplyCommand {

    private final int x;
    private final int y;
    private final int product;

    public MultiplyCommand(String input) {
        x = Integer.parseInt(input.split(",")[0].split("\\(")[1]);
        y = Integer.parseInt(input.split(",")[1].split("\\)")[0]);
        product = x * y;
    }

}
