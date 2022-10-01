package com.adventofcode.common.grid;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyMap;

public class GridPrinter {

    public static String print(Map<Point, String> grid) {
        return print(grid, emptyMap(), " ");
    }

    // Prints the grid starting with MinX,MaxY in the top left
    public static String print(Map<Point, String> grid, Map<Point, String> overrides, String defaultString) {
        int minX = grid.keySet().stream().map(p -> p.x).reduce(Integer::min).orElseThrow();
        int maxX = grid.keySet().stream().map(p -> p.x).reduce(Integer::max).orElseThrow();
        int minY = grid.keySet().stream().map(p -> p.y).reduce(Integer::min).orElseThrow();
        int maxY = grid.keySet().stream().map(p -> p.y).reduce(Integer::max).orElseThrow();
        Point point;
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n");
        for (int i = maxY; i >= minY; i--) {
            for (int j = minX; j <= maxX; j++) {
                point = new Point(j,i);
                if (overrides.containsKey(point)) {
                    sb.append(overrides.get(point));
                } else {
                    sb.append(Optional.ofNullable(grid.get(point)).orElse(defaultString));
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
