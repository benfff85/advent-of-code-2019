package com.adventofcode.day3;

import lombok.Data;

import static java.lang.Integer.parseInt;

@Data
public class Instruction {

    private Direction direction;
    private Integer magnitude;

    public Instruction (String input) {
        direction = Direction.valueOf(input.substring(0,1));
        magnitude = parseInt(input.substring(1));
    }

}
