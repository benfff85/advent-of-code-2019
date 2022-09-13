package com.adventofcode.day10;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vector {

    private final Integer reducedRise;
    private final Integer reducedRun;

}
