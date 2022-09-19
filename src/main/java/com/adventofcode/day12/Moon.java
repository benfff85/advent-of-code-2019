package com.adventofcode.day12;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Getter
public class Moon {

    private final Velocity currentVelocity = new Velocity(0,0,0);
    private final Coordinate currentCoordinate;


    public Moon(String input) {
        List<Integer> values = Arrays.stream(StringUtils.deleteAny(input, "<>zyx= ").split(",")).map(Integer::parseInt).toList();
        currentCoordinate = new Coordinate(values.get(0), values.get(1), values.get(2));
    }

}
