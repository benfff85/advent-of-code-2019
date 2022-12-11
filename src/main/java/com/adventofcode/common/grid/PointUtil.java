package com.adventofcode.common.grid;

import com.adventofcode.year2019.day3.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.adventofcode.common.grid.SurroundingType.ALL;

public class PointUtil {

    private PointUtil() {
        // Masking public constructor
    }

    public static List<Point> getSurroundingPoints(Point point, SurroundingType surroundingType) {

        List<Point> points = new ArrayList<>();

        // Get points directly above, below, left and right provided point
        points.add(new Point(point.x + 1, point.y));
        points.add(new Point(point.x - 1, point.y));
        points.add(new Point(point.x, point.y + 1));
        points.add(new Point(point.x, point.y - 1));

        // Get elements diagonal to provided point
        if (ALL.equals(surroundingType)) {
            points.add(new Point(point.x + 1, point.y + 1));
            points.add(new Point(point.x + 1, point.y - 1));
            points.add(new Point(point.x - 1, point.y + 1));
            points.add(new Point(point.x - 1, point.y - 1));
        }

        return points;

    }

    public static Point getAdjacentPoint(Point point, Direction direction) {
        return switch (direction) {
            case U -> new Point(point.x, point.y + 1);
            case D -> new Point(point.x, point.y - 1);
            case L -> new Point(point.x - 1, point.y);
            case R -> new Point(point.x + 1, point.y);
        };
    }

}
