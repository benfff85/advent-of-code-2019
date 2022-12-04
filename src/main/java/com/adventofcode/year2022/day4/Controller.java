package com.adventofcode.year2022.day4;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component("controller-2022-4")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2022/day-4.txt");
    }

    public DailyAnswer execute() {

        List<Pair> pairs = puzzleInput.stream().map(Pair::new).toList();

        answer.setPart1(pairs.stream().filter(Pair::isSubset).count());
        log.info("P1: {}", answer.getPart1());

        answer.setPart2(pairs.stream().filter(Pair::isOverlap).count());
        log.info("P2: {}", answer.getPart2());

        return answer;
    }

}
