package com.adventofcode.common.grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.adventofcode.common.grid.SurroundingType.ALL;
import static com.adventofcode.common.grid.Direction.*;

public class PointUtil {

    private PointUtil() {
        // Masking public constructor
    }

    public static List<Point> getSurroundingPoints(Point point, SurroundingType surroundingType) {

        List<Point> points = new ArrayList<>();

        // Get points directly above, below, left and right provided point
        points.add(getAdjacentPoint(point, U));
        points.add(getAdjacentPoint(point, D));
        points.add(getAdjacentPoint(point, L));
        points.add(getAdjacentPoint(point, R));

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
