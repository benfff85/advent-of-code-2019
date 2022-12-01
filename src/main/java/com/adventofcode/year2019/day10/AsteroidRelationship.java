package com.adventofcode.year2019.day10;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AsteroidRelationship implements Comparable<AsteroidRelationship> {

    private final Integer distance;
    private final Asteroid asteroid;

    @Override
    public int compareTo(AsteroidRelationship a) {
        return distance < a.getDistance() ? -1 : 1;
    }

}
