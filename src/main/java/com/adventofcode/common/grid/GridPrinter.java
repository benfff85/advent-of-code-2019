package com.adventofcode.common.grid;

import java.awt.*;
import java.util.Map;

import static java.util.Collections.emptyMap;

public class GridPrinter {

    private GridPrinter() {
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
        for (int i = maxY; i >= minY; i--) {
            for (int j = minX; j <= maxX; j++) {
                point = new Point(j, i);
                sb.append(overrides.getOrDefault(point, grid.getOrDefault(point, defaultElement)).print());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
