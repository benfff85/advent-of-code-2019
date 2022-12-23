package com.adventofcode.year2022.day15;

import com.adventofcode.common.grid.PointUtil;
import lombok.Data;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.CharSetUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Sensor {

    private final Point sensorPoint;
    private final Point beaconPoint;
    private final Integer manhattanDistance;

    public Sensor(String input) {

        List<Integer> inputInts = Arrays.stream(CharSetUtils.delete(input, CharSet.ASCII_ALPHA + ":").split("="))
                .skip(1)
                .map(Integer::parseInt)
                .toList();

        sensorPoint = new Point(inputInts.get(0), inputInts.get(1));
        beaconPoint = new Point(inputInts.get(2), inputInts.get(3));
        manhattanDistance = PointUtil.getManhattanDistance(sensorPoint, beaconPoint);

    }


    public Set<Point> getCoveredPointsOnRow(Integer y) {
        Set<Point> coveredPoints = new HashSet<>();
        for (int x = 0; x <= manhattanDistance; x++) {
            if (PointUtil.getManhattanDistance(sensorPoint, new Point(sensorPoint.x + x, y)) <= manhattanDistance) {
                coveredPoints.add(new Point(sensorPoint.x + x, y));
                coveredPoints.add(new Point(sensorPoint.x - x, y));
            } else {
                break;
            }
        }

        return coveredPoints;
    }

    // TODO add to set passed as param?
    // TODO only check elements not already in the set?
    public Set<Point> getCoveredPointsOnRow(Integer y, Set<Point> set) {
        for (int x = 0; x <= manhattanDistance; x++) {
            if (PointUtil.getManhattanDistance(sensorPoint, new Point(sensorPoint.x + x, y)) <= manhattanDistance) {
                if(sensorPoint.x + x <= 4000000 && sensorPoint.x + x >= 0) {
                    set.add(new Point(sensorPoint.x + x, y));
                }
                if(sensorPoint.x - x <= 4000000 && sensorPoint.x - x >= 0) {
                    set.add(new Point(sensorPoint.x - x, y));
                }
            } else {
                break;
            }
        }

        return set;
    }

}
