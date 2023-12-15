package com.adventofcode.year2023.day11;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.adventofcode.common.grid.GridUtility;
import com.adventofcode.common.grid.PointUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.adventofcode.year2023.day11.GridElement.GALAXY;


@Slf4j
@Component("controller-2023-11")
public class Controller extends SolutionController {


    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2023/day-11.txt");
    }

    private static long process(Map<Point, GridElement> grid, int shiftMagnitude) {
        for (int y = 0; y < GridUtility.getMaxY(grid); y++) {
            if (GridUtility.getElementsOnRow(grid, y).values().stream().noneMatch(gridElement -> gridElement.equals(GALAXY))) {
                GridUtility.shiftRows(grid, y, shiftMagnitude);
                y += shiftMagnitude;
            }
        }
        for (int x = 0; x < GridUtility.getMaxX(grid); x++) {
            if (GridUtility.getElementsInColumn(grid, x).values().stream().noneMatch(gridElement -> gridElement.equals(GALAXY))) {
                GridUtility.shiftColumns(grid, x, shiftMagnitude);
                x += shiftMagnitude;
            }
        }

        List<Point> galaxies = new ArrayList<>(GridUtility.getElementsByValue(grid, GALAXY).keySet());

        List<Pair<Point, Point>> galaxyPairs = new ArrayList<>();
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                galaxyPairs.add(Pair.of(galaxies.get(i), galaxies.get(j)));
            }
        }

        return galaxyPairs.stream().map(pair -> PointUtil.getManhattanDistance(pair.getLeft(), pair.getRight())).mapToLong(Integer::longValue).sum();
    }

    public DailyAnswer execute() {

        Map<Point, GridElement> grid = GridUtility.constructGridWithSelectElements(puzzleInput, GridElement.class, List.of(GALAXY));
        answer.setPart1(process(grid, 1));
        log.info("Part 1: {}", answer.getPart1());

        grid = GridUtility.constructGridWithSelectElements(puzzleInput, GridElement.class, List.of(GALAXY));
        answer.setPart2(process(grid, 999999));
        log.info("Part 2: {}", answer.getPart2());

        return answer;

    }

}