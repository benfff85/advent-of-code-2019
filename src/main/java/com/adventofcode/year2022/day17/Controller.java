package com.adventofcode.year2022.day17;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.adventofcode.common.grid.Direction;
import com.adventofcode.common.grid.GridUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.ResettableListIterator;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.adventofcode.year2022.day17.RockType.*;


@Slf4j
@Component("controller-2022-17")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2022/day-17.txt");
    }


    public DailyAnswer execute() {

        ResettableListIterator<Direction> directions = IteratorUtils.loopingListIterator(Arrays.stream(puzzleInput.get(0).split("")).map(DirectionMapper::getDirection).toList());
        ResettableListIterator<RockType> rockTypes = IteratorUtils.loopingListIterator(List.of(DASH, PLUS, ANGLE, LINE, SQUARE));

        Map<Point, GridElement> grid = new HashMap<>();
        initBottomWall(grid);
        addSideWalls(grid);

        Rock rock;
        // Loop through rocks
        for (int i = 0; i < 2022; i++) {
            addSideWalls(grid);
            rock = new Rock(findHeightOfRocks(grid), rockTypes.next());

            do {
                rock.move(directions.next(), grid);
            } while (rock.move(Direction.D, grid));

            grid.putAll(rock.getPoints().stream().collect(Collectors.toMap(p -> p, p -> GridElement.ROCK)));
        }


        answer.setPart1(findHeightOfRocks(grid));
        log.info("P1: {}", answer.getPart1());

        return answer;
    }


    private void initBottomWall(Map<Point, GridElement> grid) {
        for (int i = 0; i < 9; i++) {
            grid.put(new Point(i, 0), GridElement.WALL);
        }
    }

    private void addSideWalls(Map<Point, GridElement> grid) {
        Integer wallHeight = findHeightOfWalls(grid);
        int rockHeight = findHeightOfRocks(grid);

        for (int i = wallHeight; i <= rockHeight + 10; i++) {
            grid.put(new Point(0, i), GridElement.WALL);
            grid.put(new Point(8, i), GridElement.WALL);
        }

    }

    private Integer findHeightOfWalls(Map<Point, GridElement> grid) {
        return GridUtility.getAdjacentElements(grid, new Point(0, 0), Direction.U).size();
    }

    private Integer findHeightOfRocks(Map<Point, GridElement> grid) {
        return GridUtility.getMaxY(grid, gridEntry -> gridEntry.getValue().equals(GridElement.ROCK), 0);
    }

}