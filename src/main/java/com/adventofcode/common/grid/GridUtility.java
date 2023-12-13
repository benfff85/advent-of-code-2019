package com.adventofcode.common.grid;

import com.adventofcode.common.AdventOfCodeException;
import org.apache.commons.math3.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        int minX = getMinX(grid);
        int maxX = getMaxX(grid);
        int minY = getMinY(grid);
        int maxY = getMaxY(grid);

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

    public static <T> Map<Point, T> getAllSurroundingElementsExcludingProvided(Map<Point, T> grid, List<Point> points, SurroundingType surroundingType) {
        return getAllSurroundingElements(grid, points, surroundingType, false);
    }

    public static <T> Map<Point, T> getAllSurroundingElementsIncludingProvided(Map<Point, T> grid, List<Point> points, SurroundingType surroundingType) {
        return getAllSurroundingElements(grid, points, surroundingType, true);
    }

    private static <T> Map<Point, T> getAllSurroundingElements(Map<Point, T> grid, List<Point> points, SurroundingType surroundingType, boolean shouldIncludeProvided) {

        return points
                .stream()
                .flatMap(point -> getSurroundingElements(grid, point, surroundingType).entrySet().stream())
                .filter(entry -> shouldIncludeProvided || !points.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b));
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

    // Returns null if key not present
    public static <T> Map.Entry<Point, T> getAdjacentElement(Map<Point, T> grid, Point point, Direction direction) {
        try {
            return Map.entry(point, grid.get(PointUtil.getAdjacentPoint(point, direction)));
        } catch (Exception e) {
            // Ignore
        }
        return null;
    }

    public static <T> Map<Point, T> getElementsOnRow(Map<Point, T> grid, Integer y) {
        return grid.entrySet().stream().filter(p -> p.getKey().y == y).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <T> void addRow(Map<Point, T> grid, int y, int size, T gridElement) {
        shiftRows(grid, y, 1);
        IntStream.rangeClosed(0, size).forEach(x -> grid.put(new Point(x, y), gridElement));
    }

    /**
     * For a grid with (0,0) in the bottom left this will shift rows up
     */
    public static <T> void shiftRows(Map<Point, T> grid, int y, int shiftMagnitude) {
        Map<Point, T> pointsToMove = getAllElementsInDirectionRelativeToPoint(grid, new Point(0, y), Direction.U);
        pointsToMove.forEach((key, value) -> grid.remove(key));
        pointsToMove.forEach((key, value) -> grid.put(new Point(key.x, key.y + shiftMagnitude), value));
    }

    public static <T> Map<Point, T> getElementsInColumn(Map<Point, T> grid, Integer x) {
        return grid.entrySet().stream().filter(p -> p.getKey().x == x).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <T> void addColumn(Map<Point, T> grid, int x, int size, T gridElement) {
        shiftColumns(grid, x, 1);
        IntStream.rangeClosed(0, size).forEach(y -> grid.put(new Point(x, y), gridElement));
    }

    /**
     * For a grid with (0,0) in the bottom left this will shift columns to the right
     */
    public static <T> void shiftColumns(Map<Point, T> grid, int x, int shiftMagnitude) {
        Map<Point, T> pointsToMove = getAllElementsInDirectionRelativeToPoint(grid, new Point(x, 0), Direction.R);
        pointsToMove.forEach((key, value) -> grid.remove(key));
        pointsToMove.forEach((key, value) -> grid.put(new Point(key.x + shiftMagnitude, key.y), value));
    }

    /**
     * Returns all elements in a specified direction from a specific point, including the point itself. This is not bound to a row or column but will consider the full grid.
     *
     * @param grid
     * @param point
     * @param direction
     * @return
     */
    public static <T> Map<Point, T> getAllElementsInDirectionRelativeToPoint(Map<Point, T> grid, Point point, Direction direction) {
        return switch (direction) {
            case U ->
                    grid.entrySet().stream().filter(e -> e.getKey().y >= point.y).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            case D ->
                    grid.entrySet().stream().filter(e -> e.getKey().y <= point.y).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            case L ->
                    grid.entrySet().stream().filter(e -> e.getKey().x <= point.x).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            case R ->
                    grid.entrySet().stream().filter(e -> e.getKey().x >= point.x).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            default -> throw new AdventOfCodeException("Invalid direction");
        };
    }

    public static <T> Long countValues(Map<Point, T> grid, T value) {
        return grid.values().stream().filter(v -> v.equals(value)).count();
    }

    public static <T> Integer getMinX(Map<Point, T> grid) {
        return grid.keySet().stream().map(p -> p.x).reduce(Integer::min).orElseThrow();
    }

    public static <T> Integer getMaxX(Map<Point, T> grid) {
        return grid.keySet().stream().map(p -> p.x).reduce(Integer::max).orElseThrow();
    }

    public static <T> Integer getMinY(Map<Point, T> grid) {
        return grid.keySet().stream().map(p -> p.y).reduce(Integer::min).orElseThrow();
    }

    public static <T> Integer getMaxY(Map<Point, T> grid) {
        return grid.keySet().stream().map(p -> p.y).reduce(Integer::max).orElseThrow();
    }

    public static <T> Integer getMaxY(Map<Point, T> grid, Predicate<Map.Entry<Point, T>> predicate, Integer defaultResult) {
        return grid.entrySet().stream().filter(predicate).map(e -> e.getKey().y).reduce(Integer::max).orElse(defaultResult);
    }

    public static <T> Map<Point, T> getElementsByValue(Map<Point, T> grid, T input) {
        return getElementStreamByValue(grid, input).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <T> Map.Entry<Point, T> getFirstElementByValue(Map<Point, T> grid, T input) {
        return getElementStreamByValue(grid, input).findFirst().orElseThrow();
    }

    private static <T> Stream<Map.Entry<Point, T>> getElementStreamByValue(Map<Point, T> grid, T input) {
        return grid.entrySet().stream().filter(e -> e.getValue().equals(input));
    }

}
