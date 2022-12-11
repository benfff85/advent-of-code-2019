package com.adventofcode.common.grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static com.adventofcode.common.grid.SurroundingType.ALL;
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
//        Point key;



        Map<Point, PrintableGridElement> surroundingElements = new HashMap<>();

        for(Point point1 : PointUtil.getSurroundingPoints(point, surroundingType)) {
            if(grid.containsKey(point1)) {
                surroundingElements.put(point1, grid.get(point1));
            }
        }

//        for (Map.Entry<Point, PrintableGridElement> entry : grid.entrySet()) {
//            key = entry.getKey();
//            // Get elements directly above, below, left and right provided point
//            if ((key.x == point.x && (key.y == point.y - 1 || key.y == point.y + 1))
//                    || (key.y == point.y && (key.x == point.x - 1 || key.x == point.x + 1))) {
//                surroundingElements.put(key, entry.getValue());
//            }
//            // Get elements diagonal to provided point
//            if (ALL.equals(surroundingType) && (key.x == point.x - 1 || key.x == point.x + 1) && (key.y == point.y - 1 || key.y == point.y + 1)) {
//                surroundingElements.put(key, entry.getValue());
//            }
//        }
        return surroundingElements;
    }

    //get elements as list
//    public static List<Map.Entry<Point, PrintableGridElement>> getElementsAsOrderedList(Map<Point, PrintableGridElement> grid, OrderingType verticalOrdering, OrderingType horizontalOrdering) {
//        List<Map.Entry<Point, PrintableGridElement>> list = new ArrayList<>();
//
//
//        return null;
//    }

}
