package com.adventofcode.year2022.day17;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CacheResult {

    private final List<Integer> topology;
    private final Integer indexOfOriginalRock;

}
