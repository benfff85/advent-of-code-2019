package com.adventofcode.year2023.day15;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Lens {

    @EqualsAndHashCode.Include
    private final String label;
    private final int focalLength;
    private final boolean shouldAdd;

    public Lens(String input) {
        if (input.contains("=")) {

            label = input.split("=")[0];
            focalLength = Integer.parseInt(input.split("=")[1]);
            shouldAdd = true;
        } else {
            label = input.split("-")[0];
            focalLength = 0;
            shouldAdd = false;
        }
    }

}
