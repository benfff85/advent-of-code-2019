package com.adventofcode.year2022.day10;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cycle {

    private Integer number;
    private Integer x;
    private Integer signalStrength;

}
