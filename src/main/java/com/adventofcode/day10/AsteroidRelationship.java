package com.adventofcode.day10;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AsteroidRelationship {

    private final Integer distance;
    private final Asteroid asteroid;

}
