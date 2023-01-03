package com.adventofcode.year2022.day16;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CacheKey {

    private Set<Valve> openValves;
    private int minutesRemaining;
    private Valve currentValveA;
    private Valve currentValveB;

}
