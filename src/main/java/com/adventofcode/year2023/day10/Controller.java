package com.adventofcode.year2023.day10;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.adventofcode.year2023.day10.GridElement.*;


@Slf4j
@Component("controller-2023-10")
public class Controller extends SolutionController {

    private static final List<GridElement> upSupportedPipes = List.of(UD_PIPE, DR_PIPE, LD_PIPE);
    private static final List<GridElement> rightSupportedPipes = List.of(LR_PIPE, LD_PIPE, UL_PIPE);
    private static final List<GridElement> downSupportedPipes = List.of(UD_PIPE, RU_PIPE, UL_PIPE);
    private static final List<GridElement> leftSupportedPipes = List.of(LR_PIPE, RU_PIPE, DR_PIPE);


    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2023/day-10.txt");
    }

    public DailyAnswer execute() {

        Map<Point, GridElement> grid = initMap();

        log.info("{}", GridUtility.print(grid));

        Point point = GridUtility.getFirstElementByValue(grid, START).getKey();
        Point nextPoint;
        List<Point> path = new ArrayList<>();
        path.add(point);

        while (true) {

            Point finalPoint = point;
            nextPoint = GridUtility.getSurroundingElements(grid, point, SurroundingType.CARDINAL).entrySet().stream()
                    .filter(e -> !path.contains(e.getKey()))
                    .filter(e -> !e.getValue().equals(AIR))
                    .filter(e -> isConnected(grid, finalPoint, e))
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(null);

            if (nextPoint == null) {
                break;
            }

            path.add(nextPoint);
            point = nextPoint;

        }


        answer.setPart1((path.size() + 1) / 2);
        log.info("Part 1: {}", answer.getPart1());

        answer.setPart2(0);
        log.info("Part 2: {}", answer.getPart2());

        return answer;

    }

    private boolean isConnected(Map<Point, GridElement> grid, Point point, Map.Entry<Point, GridElement> e) {
        Direction direction = PointUtil.getDirection(point, e.getKey());
        assert direction != null;
        GridElement lastElement = grid.get(point);

        // LR_PIPE("-"), UD_PIPE("|"), DR_PIPE("F"), LD_PIPE("7"), RU_PIPE("L"), UL_PIPE("J"), AIR("."), START("S");
        if (lastElement.equals(START)) {
            return switch (direction) {
                case U -> upSupportedPipes.contains(e.getValue());
                case R -> rightSupportedPipes.contains(e.getValue());
                case D -> downSupportedPipes.contains(e.getValue());
                case L -> leftSupportedPipes.contains(e.getValue());
                default -> false;
            };
        }

        if (lastElement.equals(LR_PIPE)) {
            return switch (direction) {
                case R -> rightSupportedPipes.contains(e.getValue());
                case L -> leftSupportedPipes.contains(e.getValue());
                default -> false;
            };
        }

        if (lastElement.equals(UD_PIPE)) {
            return switch (direction) {
                case U -> upSupportedPipes.contains(e.getValue());
                case D -> downSupportedPipes.contains(e.getValue());
                default -> false;
            };
        }

        if (lastElement.equals(DR_PIPE)) {
            return switch (direction) {
                case R -> rightSupportedPipes.contains(e.getValue());
                case D -> downSupportedPipes.contains(e.getValue());
                default -> false;
            };
        }

        if (lastElement.equals(LD_PIPE)) {
            return switch (direction) {
                case D -> downSupportedPipes.contains(e.getValue());
                case L -> leftSupportedPipes.contains(e.getValue());
                default -> false;
            };
        }

        if (lastElement.equals(RU_PIPE)) {
            return switch (direction) {
                case U -> upSupportedPipes.contains(e.getValue());
                case R -> rightSupportedPipes.contains(e.getValue());
                default -> false;
            };
        }

        if (lastElement.equals(UL_PIPE)) {
            return switch (direction) {
                case U -> upSupportedPipes.contains(e.getValue());
                case L -> leftSupportedPipes.contains(e.getValue());
                default -> false;
            };
        }

        return false;

    }

    private Map<Point, GridElement> initMap() {
        Map<Point, GridElement> map = new HashMap<>();

        String[] row;
        for (int y = puzzleInput.size() - 1; y >= 0; y--) {
            row = puzzleInput.get(puzzleInput.size() - (y + 1)).split("");
            for (int x = 0; x < puzzleInput.getFirst().length(); x++) {
                map.put(new Point(x, y), fromString(row[x]));
            }
        }
        return map;
    }
}