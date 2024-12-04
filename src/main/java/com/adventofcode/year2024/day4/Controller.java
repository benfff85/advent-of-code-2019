package com.adventofcode.year2024.day4;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.adventofcode.common.grid.Direction;
import com.adventofcode.common.grid.GridUtility;
import com.adventofcode.common.grid.PointUtil;
import com.adventofcode.common.grid.SurroundingType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;
import java.util.Map;

import static com.adventofcode.common.grid.GridUtility.constructGrid;
import static com.adventofcode.common.grid.GridUtility.getSurroundingElements;

@Slf4j
@Component("controller-2024-4")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2024/day-4.txt");
    }

    public DailyAnswer execute() {

        Map<Point, GridElement> grid = constructGrid(puzzleInput, GridElement.class);

        int count = 0;
        List<Map.Entry<Point, GridElement>> fragment;
        for(Point point : grid.keySet()) {
            fragment = GridUtility.getAdjacentElements(grid, point, Direction.L, 4);
            if(isXmas(fragment)) {count++;}

            fragment = GridUtility.getAdjacentElements(grid, point, Direction.U, 4);
            if(isXmas(fragment)) {count++;}

            fragment = GridUtility.getAdjacentElements(grid, point, Direction.R, 4);
            if(isXmas(fragment)) {count++;}

            fragment = GridUtility.getAdjacentElements(grid, point, Direction.D,4);
            if(isXmas(fragment)) {count++;}

            fragment = GridUtility.getAdjacentElements(grid, point, Direction.UL, 4);
            if(isXmas(fragment)) {count++;}

            fragment = GridUtility.getAdjacentElements(grid, point, Direction.UR, 4);
            if(isXmas(fragment)) {count++;}

            fragment = GridUtility.getAdjacentElements(grid, point, Direction.DL, 4);
            if(isXmas(fragment)) {count++;}

            fragment = GridUtility.getAdjacentElements(grid, point, Direction.DR, 4);
            if(isXmas(fragment)) {count++;}

        }
        answer.setPart1(count);
        log.info("Part 1: {}", answer.getPart1());

        count=0;
        for(Point point : grid.keySet()) {
            Map<Point, GridElement> surround = getSurroundingElements(grid, point, SurroundingType.ALL);
            if(GridElement.A.equals(grid.get(point)) && (

                    (GridElement.M.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.UL))) &&
                    GridElement.M.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.DL))) &&
                    GridElement.S.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.UR))) &&
                    GridElement.S.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.DR)))) ||

                    (GridElement.M.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.UL))) &&
                    GridElement.S.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.DL))) &&
                    GridElement.M.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.UR))) &&
                    GridElement.S.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.DR)))) ||

                    (GridElement.S.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.UL))) &&
                    GridElement.M.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.DL))) &&
                    GridElement.S.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.UR))) &&
                    GridElement.M.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.DR)))) ||

                    (GridElement.S.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.UL))) &&
                    GridElement.S.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.DL))) &&
                    GridElement.M.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.UR))) &&
                    GridElement.M.equals(surround.get(PointUtil.getAdjacentPoint(point, Direction.DR))))
            )){
                count++;
            }

        }

        answer.setPart2(count);
        log.info("Part 2: {}", answer.getPart2());

        return answer;
    }

    private boolean isXmas(List<Map.Entry<Point, GridElement>> fragment) {
        return fragment.stream().map(Map.Entry::getValue).toList().equals(List.of(GridElement.X, GridElement.M, GridElement.A, GridElement.S));
    }


}
