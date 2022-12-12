package com.adventofcode.common.grid;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

public class GridUtility {

    private GridUtility() {
        // Masking default constructor
    }

    public static String print(Map<Point, PrintableGridElement> grid) {
        return print(grid, emptyMap(), SimplePrintableGridElement.of(" "));
    }

    // Prints the grid starting with MinX,MaxY in the top left
    public static String print(Map<Point, PrintableGridElement> grid, Map<Point, PrintableGridElement> overrides, PrintableGridElement defaultElement) {

        int minX = grid.keySet().stream().map(p -> p.x).reduce(Integer::min).orElseThrow();
        int maxX = grid.keySet().stream().map(p -> p.x).reduce(Integer::max).orElseThrow();
        int minY = grid.keySet().stream().map(p -> p.y).reduce(Integer::min).orElseThrow();
        int maxY = grid.keySet().stream().map(p -> p.y).reduce(Integer::max).orElseThrow();

        Point point;
        StringBuilder sb = new StringBuilder().append("\n\n");
        PrintableGridElement element;
        for (int i = maxY; i >= minY; i--) {
            for (int j = minX; j <= maxX; j++) {
                point = new Point(j, i);
                element = overrides.getOrDefault(point, grid.getOrDefault(point, defaultElement));
                // Above catches if point is missing from grid, below catches if point is present but has a null value
                if (element == null) {
                    element = defaultElement;
                }
                sb.append(element.print());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static Map<Point, PrintableGridElement> getSurroundingElements(Map<Point, PrintableGridElement> grid, Point point, SurroundingType surroundingType) {

        Map<Point, PrintableGridElement> surroundingElements = new HashMap<>();

        for(Point point1 : PointUtil.getSurroundingPoints(point, surroundingType)) {
            if (grid.containsKey(point1)) {
                surroundingElements.put(point1, grid.get(point1));
            }
        }
        return surroundingElements;
    }

}
