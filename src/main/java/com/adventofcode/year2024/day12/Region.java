package com.adventofcode.year2024.day12;

import com.adventofcode.common.grid.Direction;
import com.adventofcode.common.grid.PointUtil;
import lombok.Data;

import java.awt.*;
import java.util.Set;

@Data
public class Region {

    private String value;
    private Set<Point> points;
    private Integer area;
    private Integer perimeter;
    private Integer price;

    public Region(Set<Point> points, String value) {
        this.points = points;
        this.value = value;

        area = points.size();

        // calculate perimeter
        perimeter = 0;
        for(Point point : points) {
            Point adjacentPoint = PointUtil.getAdjacentPoint(point, Direction.U);
            if(!points.contains(adjacentPoint)) {
                perimeter++;
            }
            adjacentPoint = PointUtil.getAdjacentPoint(point, Direction.D);
            if(!points.contains(adjacentPoint)) {
                perimeter++;
            }
            adjacentPoint = PointUtil.getAdjacentPoint(point, Direction.L);
            if(!points.contains(adjacentPoint)) {
                perimeter++;
            }
            adjacentPoint = PointUtil.getAdjacentPoint(point, Direction.R);
            if(!points.contains(adjacentPoint)) {
                perimeter++;
            }
        }

        price = area * perimeter;
    }

}
