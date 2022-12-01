package com.adventofcode.year2019.day15;

import com.adventofcode.common.grid.PrintableGridElement;
import com.adventofcode.year2019.day3.Direction;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.adventofcode.year2019.day3.Direction.*;

public class OxygenSimulator {


    public int run(Map<Point, PrintableGridElement> grid) {
        int count = 0;
        List<Point> pointsToFlip = new LinkedList<>();

        do {
            count++;
            pointsToFlip.clear();

            // Loop through all points and find which to flip
            Point point;
            for (Map.Entry<Point, PrintableGridElement> entry : grid.entrySet()) {
                point = entry.getKey();
                if (entry.getValue().equals(GridElement.EMPTY) && atLeastOneAdjacentPointIsOxygen(grid, point)) {
                    pointsToFlip.add(entry.getKey());
                }
            }

            // Flip said points
            for (Point pointToFlip : pointsToFlip) {
                grid.put(pointToFlip, GridElement.OXYGEN);
            }

        } while (!pointsToFlip.isEmpty());
        count--;

        return count;

    }

    private boolean atLeastOneAdjacentPointIsOxygen(Map<Point, PrintableGridElement> grid, Point point) {
        return grid.getOrDefault(getAdjacentPoint(U, point), GridElement.WALL).equals(GridElement.OXYGEN)
                || grid.getOrDefault(getAdjacentPoint(D, point), GridElement.WALL).equals(GridElement.OXYGEN)
                || grid.getOrDefault(getAdjacentPoint(L, point), GridElement.WALL).equals(GridElement.OXYGEN)
                || grid.getOrDefault(getAdjacentPoint(R, point), GridElement.WALL).equals(GridElement.OXYGEN);
    }


    private Point getAdjacentPoint(Direction direction, Point point) {
        return switch (direction) {
            case U -> new Point(point.x, point.y + 1);
            case D -> new Point(point.x, point.y - 1);
            case L -> new Point(point.x - 1, point.y);
            case R -> new Point(point.x + 1, point.y);
        };
    }

}
