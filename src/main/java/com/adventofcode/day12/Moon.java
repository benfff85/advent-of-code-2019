package com.adventofcode.day12;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static com.adventofcode.day12.Axis.X;
import static com.adventofcode.day12.Axis.Y;

@Getter
public class Moon {

    private final Velocity currentVelocity = new Velocity(0, 0, 0);
    private final Coordinate currentCoordinate;

    public Moon(String input) {
        List<Integer> values = Arrays.stream(StringUtils.deleteAny(input, "<>zyx= ").split(",")).map(Integer::parseInt).toList();
        currentCoordinate = new Coordinate(values.get(0), values.get(1), values.get(2));
    }

    public Vector getVector(Axis axis) {
        if (X.equals(axis)) {
            return new Vector(currentCoordinate.getX(), currentVelocity.getX());
        } else if (Y.equals(axis)) {
            return new Vector(currentCoordinate.getY(), currentVelocity.getY());
        } else {
            return new Vector(currentCoordinate.getZ(), currentVelocity.getZ());
        }
    }

}
