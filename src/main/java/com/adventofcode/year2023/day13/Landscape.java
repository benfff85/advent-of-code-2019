package com.adventofcode.year2023.day13;

import com.adventofcode.common.grid.GridUtility;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Slf4j
@Data
public class Landscape {

    private Map<Point, GridElement> grid = new HashMap<>();

    private SymmetryData initialSymmetryData;
    private SymmetryData secondarySymmetryData;

    public Landscape(List<String> input) {
        initMap(input);
        initialSymmetryData = calculateSymmetry(false);

        Set<Point> rockPoints = GridUtility.getElementsByValue(grid, GridElement.ROCK).keySet();
        for (Point rock : rockPoints) {
            grid.remove(rock);
            secondarySymmetryData = calculateSymmetry(true);
            grid.put(rock, GridElement.ROCK);
            if (nonNull(secondarySymmetryData)) {
                break;
            }
        }

    }

    private void initMap(List<String> input) {
        String[] row;
        GridElement gridElement;
        for (int y = input.size() - 1; y >= 0; y--) {
            row = input.get(input.size() - (y + 1)).split("");
            for (int x = 0; x < input.getFirst().length(); x++) {
                gridElement = GridElement.fromString(row[x]);
                if (gridElement.equals(GridElement.ROCK)) {
                    grid.put(new Point(x, y), gridElement);
                }
            }
        }
    }


    private SymmetryData calculateSymmetry(boolean excludeInitialSymmetry) {

        SymmetryData result = checkForVerticalSymmetry(excludeInitialSymmetry);
        if (nonNull(result)) {
            return result;
        }
        return checkForHorizontalSymmetry(excludeInitialSymmetry);

    }

    private SymmetryData checkForVerticalSymmetry(boolean excludeInitialSymmetry) {
        int maxX = GridUtility.getMaxX(grid);
        int colToLeft;
        int colToRight;
        int colToCheck;
        boolean isSymmetrical;
        for (int x = 0; x < maxX; x++) {

            if (excludeInitialSymmetry && x == initialSymmetryData.getSymmetryIndex() && initialSymmetryData.getSymmetryType().equals(SymmetryType.VERTICAL)) {
                continue;
            }

            colToLeft = x + 1;
            colToRight = (maxX - colToLeft) + 1;
            colToCheck = Math.min(colToLeft, colToRight);
            isSymmetrical = true;
            for (int i = 1; i <= colToCheck; i++) {
                Set<Integer> l = GridUtility.getElementsInColumn(grid, (x + 1) - i).keySet().stream().map(gridElement -> gridElement.y).collect(Collectors.toSet());
                Set<Integer> r = GridUtility.getElementsInColumn(grid, x + i).keySet().stream().map(gridElement -> gridElement.y).collect(Collectors.toSet());
                if (!l.equals(r)) {
                    isSymmetrical = false;
                    break;
                }
            }
            if (isSymmetrical) {
                log.info("Found vertical symmetry line at x={}", x);
                return SymmetryData.builder().symmetryValue(colToLeft).symmetryIndex(x).symmetryType(SymmetryType.VERTICAL).build();
            }
        }
        return null;
    }

    private SymmetryData checkForHorizontalSymmetry(boolean excludeInitialSymmetry) {
        int maxY = GridUtility.getMaxY(grid);
        int rowBelow;
        int rowAbove;
        int rowToCheck;
        boolean isSymmetrical;
        for (int y = 0; y < maxY; y++) {

            if (excludeInitialSymmetry && y == initialSymmetryData.getSymmetryIndex() && initialSymmetryData.getSymmetryType().equals(SymmetryType.HORIZONTAL)) {
                continue;
            }

            rowBelow = y + 1;
            rowAbove = (maxY - rowBelow) + 1;
            rowToCheck = Math.min(rowBelow, rowAbove);
            isSymmetrical = true;
            for (int i = 1; i <= rowToCheck; i++) {
                Set<Integer> b = GridUtility.getElementsOnRow(grid, (y + 1) - i).keySet().stream().map(gridElement -> gridElement.x).collect(Collectors.toSet());
                Set<Integer> a = GridUtility.getElementsOnRow(grid, y + i).keySet().stream().map(gridElement -> gridElement.x).collect(Collectors.toSet());
                if (!b.equals(a)) {
                    isSymmetrical = false;
                    break;
                }
            }
            if (isSymmetrical) {
                log.info("Found horizontal symmetry line at y={}", y);
                return SymmetryData.builder().symmetryValue(100 * rowAbove).symmetryIndex(y).symmetryType(SymmetryType.HORIZONTAL).build();

            }
        }
        return null;
    }

}
