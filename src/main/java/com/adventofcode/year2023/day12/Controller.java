package com.adventofcode.year2023.day12;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component("controller-2023-12")
public class Controller extends SolutionController {


    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2023/day-12.txt");
    }


    public DailyAnswer execute() {

        List<SpringRow> springs = puzzleInput.stream().map(SpringRow::new).toList();

        answer.setPart1(springs.stream().mapToInt(s -> s.getCombinations().size()).sum());
        log.info("Part 1: {}", answer.getPart1());

        answer.setPart2(0);
        log.info("Part 2: {}", answer.getPart2());

        return answer;

    }

}