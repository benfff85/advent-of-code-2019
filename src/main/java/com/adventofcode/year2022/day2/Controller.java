package com.adventofcode.year2022.day2;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Slf4j
@Component("controller-2022-2")
public class Controller extends SolutionController {

    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2022/day-2.txt");
    }

    public DailyAnswer execute() {

        List<Round> roundsPart1 = new LinkedList<>();
        List<Round> roundsPart2 = new LinkedList<>();
        for(String inputString: puzzleInput) {
            String[] keys = inputString.split(" ");
            roundsPart1.add(new Round(keys[0], keys[1], "A"));
            roundsPart2.add(new Round(keys[0], keys[1], "B"));
        }

        answer.setPart1(roundsPart1.stream().map(Round::getRoundScore).mapToInt(Integer::intValue).sum());
        log.info("P1: {}", answer.getPart1());

        answer.setPart2(roundsPart2.stream().map(Round::getRoundScore).mapToInt(Integer::intValue).sum());
        log.info("P2: {}", answer.getPart2());

        return answer;
    }

}
