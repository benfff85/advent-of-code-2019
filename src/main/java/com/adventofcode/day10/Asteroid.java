package com.adventofcode.day10;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Asteroid {

    private final Integer xCord;
    private final Integer yCord;
    private final String name;

    private final Map<Vector, AsteroidRelationship> visibleAsteroids;

    public Asteroid(Integer x, Integer y) {
        xCord = x;
        yCord = y;
        name = x + ":" + y;
        visibleAsteroids = new HashMap<>();
    }

    public void addVisibleAsteroid(Vector vector, AsteroidRelationship asteroidRelationship) {
        visibleAsteroids.put(vector, asteroidRelationship);
    }

    public AsteroidRelationship getVisibleAsteroid(Vector vector) {
        return visibleAsteroids.get(vector);
    }

}
