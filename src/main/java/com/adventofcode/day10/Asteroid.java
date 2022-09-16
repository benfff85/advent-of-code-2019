package com.adventofcode.day10;

import lombok.Getter;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

import static java.util.Objects.isNull;

@Getter
public class Asteroid {

    private final Integer xCord;
    private final Integer yCord;
    private final String name;

    private final Map<Double, Queue<AsteroidRelationship>> visibleAsteroids;

    public Asteroid(Integer x, Integer y) {
        xCord = x;
        yCord = y;
        name = x + ":" + y;
        visibleAsteroids = new TreeMap<>();
    }

    public void addVisibleAsteroid(Double degrees, AsteroidRelationship asteroidRelationship) {
        if (isNull(visibleAsteroids.get(degrees))) {
            visibleAsteroids.put(degrees, new PriorityQueue<>());
        }
        visibleAsteroids.get(degrees).add(asteroidRelationship);
    }

}
