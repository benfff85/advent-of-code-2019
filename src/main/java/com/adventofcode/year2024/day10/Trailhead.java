package com.adventofcode.year2024.day10;

import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Data
@Builder
public class Trailhead {

    private Point startingPoint;
    private Long score;

}
