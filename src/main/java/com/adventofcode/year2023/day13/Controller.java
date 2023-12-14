package com.adventofcode.year2023.day13;

import com.adventofcode.common.DailyAnswer;
import com.adventofcode.common.InputHelper;
import com.adventofcode.common.SolutionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component("controller-2023-13")
public class Controller extends SolutionController {


    public Controller(InputHelper inputHelper) {
        super(inputHelper, "puzzle-input/2023/day-13.txt");
    }

    public DailyAnswer execute() {

        List<Landscape> landscapes = new ArrayList<>();
        List<String> landscapeInput = new ArrayList<>();
        for (String input : puzzleInput) {
            if (input.isBlank()) {
                landscapes.add(new Landscape(landscapeInput));
                landscapeInput.clear();
            } else {
                landscapeInput.add(input);
            }
        }
        landscapes.add(new Landscape(landscapeInput));

        answer.setPart1(landscapes.stream().mapToInt(l -> l.getInitialSymmetryData().getSymmetryValue()).sum());
        log.info("Part 1: {}", answer.getPart1());

        answer.setPart2(landscapes.stream().mapToInt(l -> l.getSecondarySymmetryData().getSymmetryValue()).sum());
        log.info("Part 2: {}", answer.getPart2());

        return answer;

    }

}