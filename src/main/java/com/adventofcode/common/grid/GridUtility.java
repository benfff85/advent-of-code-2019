package com.adventofcode.common.grid;

import org.apache.commons.math3.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyMap;
import static java.util.Objects.nonNull;

public class GridUtility {

    private GridUtility() {
        // Masking default constructor
    }

    public static <T extends PrintableGridElement> String print(Map<Point, T> grid) {
        return print(grid, emptyMap(), SimplePrintableGridElement.of(" "));
    }

    // Prints the grid starting with MinX,MaxY in the top left
    public static <T extends PrintableGridElement> String print(Map<Point, T> grid, Map<Point, T> overrides, PrintableGridElement defaultElement) {

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
                element = overrides.getOrDefault(point, grid.getOrDefault(point, null));
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

    public static <T> Map<Point, T> getSurroundingElements(Map<Point, T> grid, Point point, SurroundingType surroundingType) {

        return PointUtil.getSurroundingPoints(point, surroundingType)
                .stream()
                .map(p -> Pair.create(p, grid.get(p)))
                .filter(e -> nonNull(e.getValue()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

    }

    // Returns all elements in a specified direction from the specific point, path terminates when point is not in grid or when element of point is null. Specific element will be first in the list.
    // TODO fix size
    public static <T> List<Map.Entry<Point, T>> getAdjacentElements(Map<Point, T> grid, Point point, Direction direction) {

        List<Map.Entry<Point, T>> elementList = new ArrayList<>();

        Point trackingPoint = point;
        T trackingElement = grid.get(point);

        while (nonNull(trackingElement)) {
            elementList.add(Map.entry(trackingPoint, trackingElement));
            trackingPoint = PointUtil.getAdjacentPoint(trackingPoint, direction);
            trackingElement = grid.get(trackingPoint);
        }

        return elementList;

    }

}
