package com.adventofcode.common.grid;

import java.awt.*;
import java.util.List;

public class PointUtil {

    private PointUtil() {
        // Masking public constructor
    }

    public static List<Point> getSurroundingPoints(Point point, SurroundingType surroundingType) {

        List<Direction> directions = switch (surroundingType) {
            case CARDINAL -> Direction.allCardinal();
            case DIAGONAL -> Direction.allDiagonal();
            case ALL -> Direction.all();
        };

        return directions.stream().map(direction -> getAdjacentPoint(point, direction)).toList();

    }

    public static Point getAdjacentPoint(Point point, Direction direction) {
        return switch (direction) {
            case U -> new Point(point.x, point.y + 1);
            case D -> new Point(point.x, point.y - 1);
            case L -> new Point(point.x - 1, point.y);
            case R -> new Point(point.x + 1, point.y);
            case UL -> new Point(point.x - 1, point.y + 1);
            case UR -> new Point(point.x + 1, point.y + 1);
            case DL -> new Point(point.x - 1, point.y - 1);
            case DR -> new Point(point.x + 1, point.y - 1);
        };
    }

}
