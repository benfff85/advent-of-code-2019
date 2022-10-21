package com.adventofcode.day11;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.adventofcode.common.grid.GridUtility;
import com.adventofcode.common.grid.PrintableGridElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.adventofcode.day11.Color.BLACK;
import static com.adventofcode.day11.Color.WHITE;


@Slf4j
@Component("controller-day-11")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/day-11.txt");
    }

    public DailyAnswer execute() {

        List<BigInteger> input = inputHelper.parseStringToBigIntList(puzzleInput.get(0));

        Robot robot = new Robot();
        answer.setPart1(robot.process(input, BLACK).values().stream().filter(Panel::isHasBeenPainted).count());

        robot = new Robot();
        Map<Point, Panel> panels = robot.process(input, WHITE);
        Map<Point, PrintableGridElement> grid = new HashMap<>(panels);
        answer.setPart2(GridUtility.print(grid));
        log.info("{}", answer.getPart2());

        return answer;

    }

}
