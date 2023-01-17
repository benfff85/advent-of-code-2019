package com.adventofcode.year2022.day17;

import com.adventofcode.common.AdventOfCodeException;
import com.adventofcode.common.grid.Direction;

public class DirectionMapper {

    private DirectionMapper() {
        // Masking default
    }

    public static Direction getDirection(String input) {
        if ("<".equals(input)) {
            return Direction.L;
        } else if (">".equals(input)) {
            return Direction.R;
        } else {
            throw new AdventOfCodeException("Invalid character cannot be mapped to direction");
        }
    }

}
