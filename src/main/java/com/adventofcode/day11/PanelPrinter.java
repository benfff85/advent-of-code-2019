package com.adventofcode.day11;


import java.awt.*;
import java.util.Map;

import static com.adventofcode.day11.Color.WHITE;
import static java.util.Objects.nonNull;

public class PanelPrinter {

    private PanelPrinter() {
        // Masking default constructor
    }

    public static String print(Map<Point, Panel> panels) {
        int minX = panels.keySet().stream().map(p -> p.x).reduce(Integer::min).orElseThrow();
        int maxX = panels.keySet().stream().map(p -> p.x).reduce(Integer::max).orElseThrow();
        int minY = panels.keySet().stream().map(p -> p.y).reduce(Integer::min).orElseThrow();
        int maxY = panels.keySet().stream().map(p -> p.y).reduce(Integer::max).orElseThrow();

        Point point;
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n");
        for (int i = maxY; i >= minY; i--) {
            for (int j = minX; j <= maxX; j++) {
                point = new Point(j, i);
                if (nonNull(panels.get(point)) && WHITE.equals(panels.get(point).getColor())) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
