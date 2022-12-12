package com.adventofcode.year2019.day18;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import com.adventofcode.common.grid.GridUtility;
import com.adventofcode.common.grid.PrintableGridElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component("controller-day-18")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2019/day-18.txt");
    }

    public DailyAnswer execute() {

        Map<Point, PrintableGridElement> grid = new HashMap<>();

        String row;
        String element;
//        for (int i = 0; i < puzzleInput.size(); i++) {
//            row = puzzleInput.get(i);
//            for (int j = 0; j < row.length(); j++) {
//                element = String.valueOf(row.charAt(j));
//                if(element.equals())
//                    // TODO how to handle keys / locks
//                grid.put(new Point(i * -1, j), GridElement.getFromPrintableValue(element));
//            }
//        }

        // TODO find all possible paths to next element

        log.info(GridUtility.print(grid));

        return answer;
    }

}
